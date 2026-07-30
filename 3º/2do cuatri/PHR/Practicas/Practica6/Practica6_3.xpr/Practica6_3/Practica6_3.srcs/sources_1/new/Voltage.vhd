----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 13.04.2026 21:32:12
-- Design Name: 
-- Module Name: voltage - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
---------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity S7_Segmt is
    port ( 
        A3, A2, A1, A0: in std_logic;
        S1, S0: in std_logic;
        in_point : in std_logic; 
        SSEG_AN0, SSEG_AN1, SSEG_AN2, SSEG_AN3: out std_logic;
        point, g, f, e, d, c, b, a: out std_logic
    );
end S7_Segmt;

architecture Behavioral of S7_Segmt is  
    signal selDisplay: std_logic_vector(1 downto 0 );
    signal outDisplay: std_logic_vector(3 downto 0);
    signal selDigit:   std_logic_vector(3 downto 0);
    signal outDigit:   std_logic_vector(6 downto 0); 
begin

    selDisplay <= S1 & S0;
    selDigit <= A3 & A2 & A1 & A0;
    
    with selDisplay select  
        outDisplay <= "1110" when "00",
                      "1101" when "01",
                      "1011" when "10",
                      "0111" when "11",
                      "1111" when others;
    
    with selDigit select    
        outDigit <= "1000000" when "0000", -- 0
                    "1111001" when "0001", -- 1
                    "0100100" when "0010", -- 2
                    "0110000" when "0011", -- 3
                    "0011001" when "0100", -- 4
                    "0010010" when "0101", -- 5
                    "0000010" when "0110", -- 6
                    "1111000" when "0111", -- 7
                    "0000000" when "1000", -- 8
                    "0010000" when "1001", -- 9
                    "0001000" when "1010", -- A
                    "0000011" when "1011", -- b
                    "1000110" when "1100", -- C
                    "0100001" when "1101", -- d
                    "0000110" when "1110", -- E
                    "0001110" when "1111", -- F
                    "1111111" when others;

    SSEG_AN3 <= outDisplay(3);
    SSEG_AN2 <= outDisplay(2);
    SSEG_AN1 <= outDisplay(1);
    SSEG_AN0 <= outDisplay(0);
    
    point <= in_point;
    
    g <= outDigit(6); f <= outDigit(5); e <= outDigit(4);
    d <= outDigit(3); c <= outDigit(2); b <= outDigit(1);
    a <= outDigit(0);
    
end architecture Behavioral;



library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity conversor is
    Port(
        clk      : in  std_logic;
        vauxp6   : in  std_logic; 
        vauxn6   : in  std_logic; 
        SSEG_AN0 : out std_logic;
        SSEG_AN1 : out std_logic;
        SSEG_AN2 : out std_logic;
        SSEG_AN3 : out std_logic;
        a, b, c, d, e, f, g : out std_logic;
        point    : out std_logic
    );
end conversor;

architecture Behavioral of conversor is

    component xadc_wiz_0
        port (
            daddr_in    : in  std_logic_vector(6 downto 0);
            den_in      : in  std_logic;
            di_in       : in  std_logic_vector(15 downto 0);
            dwe_in      : in  std_logic;
            do_out      : out std_logic_vector(15 downto 0);
            drdy_out    : out std_logic;
            dclk_in     : in  std_logic;
            reset_in    : in  std_logic;
            vauxp6      : in  std_logic;
            vauxn6      : in  std_logic;
            eoc_out     : out std_logic;
            vp_in       : in  std_logic;
            vn_in       : in  std_logic
        );
    end component;

    signal eoc_sig      : std_logic;
    signal dato_adc     : std_logic_vector(15 downto 0);
    signal volt_mV      : unsigned(11 downto 0);
    
    signal dec_volts, dec_decimas, dec_centimas, dec_milis : integer range 0 to 9;

    signal refresh_counter : unsigned(19 downto 0) := (others => '0');
    signal sel_display     : std_logic_vector(1 downto 0);
    signal current_bcd     : std_logic_vector(3 downto 0);
    signal sig_point       : std_logic;

begin 

    XADC_inst : xadc_wiz_0
    port map(
        dclk_in     => clk,
        daddr_in    => "0010110", -- Canal 6
        den_in      => eoc_sig,
        di_in       => (others => '0'),
        dwe_in      => '0',
        reset_in    => '0',
        vauxp6      => vauxp6,
        vauxn6      => vauxn6,
        vp_in       => '0',
        vn_in       => '0',
        do_out      => dato_adc,       
        eoc_out     => eoc_sig,
        drdy_out    => open
    );

    process(clk)
        variable mult : unsigned(23 downto 0);
    begin
        if rising_edge(clk) then
            -- (Valor ADC * 3300) / 4096
            mult := unsigned(dato_adc(15 downto 4)) * to_unsigned(3300, 12);
            volt_mV <= mult(23 downto 12); 
        end if;
    end process;

    -- BCD
    process(volt_mV)
        variable temp : integer;
    begin
        temp := to_integer(volt_mV);
        dec_volts    <= temp / 1000;
        dec_decimas  <= (temp / 100) rem 10;
        dec_centimas <= (temp / 10) rem 10;
        dec_milis    <= temp rem 10;
    end process;

    process(clk)
    begin
        if rising_edge(clk) then
            refresh_counter <= refresh_counter + 1;
        end if;
    end process;

    sel_display <= std_logic_vector(refresh_counter(19 downto 18));
    
    with sel_display select
        current_bcd <= std_logic_vector(to_unsigned(dec_milis, 4))    when "00",
                       std_logic_vector(to_unsigned(dec_centimas, 4)) when "01",
                       std_logic_vector(to_unsigned(dec_decimas, 4))  when "10",
                       std_logic_vector(to_unsigned(dec_volts, 4))    when "11",
                       "0000" when others;

    sig_point <= '0' when sel_display = "11" else '1';

    Display_inst : entity work.S7_Segmt(Behavioral)
    port map (
        A3 => current_bcd(3), A2 => current_bcd(2), A1 => current_bcd(1), A0 => current_bcd(0),
        S1 => sel_display(1), S0 => sel_display(0),
        in_point => sig_point,
        SSEG_AN0 => SSEG_AN0, SSEG_AN1 => SSEG_AN1, SSEG_AN2 => SSEG_AN2, SSEG_AN3 => SSEG_AN3,
        point => point, a => a, b => b, c => c, d => d, e => e, f => f, g => g
    );

end architecture Behavioral;