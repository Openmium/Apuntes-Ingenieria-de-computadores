library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity S7_Segmt is
    port ( 
        A3, A2, A1, A0: in std_logic; -- Entradas de datos (Switches 0-3)
        S1, S0: in std_logic;         -- Selector de display (Switches 4-5)
        SSEG_AN0, SSEG_AN1, SSEG_AN2, SSEG_AN3: out std_logic; -- Ánodos
        point, g, f, e, d, c, b, a: out std_logic             -- Cátodos
    );
end S7_Segmt;

architecture Behavioral of S7_Segmt is  
    signal selDisplay: std_logic_vector(1 downto 0 );
    signal outDisplay: std_logic_vector(3 downto 0);
    signal selDigit:   std_logic_vector(3 downto 0);
    signal outDigit:   std_logic_vector(7 downto 0);
begin

    -- UNIÓN DE CABLES: Muy importante para que Vivado no borre la lógica
    selDisplay <= S1 & S0;
    selDigit   <= A3 & A2 & A1 & A0;
    
    -- LÓGICA DE ÁNODOS (Selección de display)
    with selDisplay select  
        outDisplay <= "1110" when "00", -- Activa AN0
                      "1101" when "01", -- Activa AN1
                      "1011" when "10", -- Activa AN2
                      "0111" when "11", -- Activa AN3
                      "1111" when others;
    
    -- LÓGICA DE CÁTODOS (Decodificador de 7 segmentos)
    -- Formato: point & g & f & e & d & c & b & a
    with selDigit select    
        outDigit <= "11000000" when "0000", -- 0
                    "11111001" when "0001", -- 1
                    "10100100" when "0010", -- 2
                    "10110000" when "0011", -- 3
                    "10011001" when "0100", -- 4
                    "10010010" when "0101", -- 5
                    "10000010" when "0110", -- 6
                    "11111000" when "0111", -- 7
                    "10000000" when "1000", -- 8
                    "10010000" when "1001", -- 9
                    "10001000" when "1010", -- A
                    "10000011" when "1011", -- b
                    "11000110" when "1100", -- C
                    "10100001" when "1101", -- d
                    "10000110" when "1110", -- E
                    "10001110" when "1111", -- F
                    "11111111" when others;

    -- SALIDA A PINES FÍSICOS
    SSEG_AN3 <= outDisplay(3);
    SSEG_AN2 <= outDisplay(2);
    SSEG_AN1 <= outDisplay(1);
    SSEG_AN0 <= outDisplay(0);
    
    point <= outDigit(7);
    g     <= outDigit(6);
    f     <= outDigit(5);
    e     <= outDigit(4);
    d     <= outDigit(3);
    c     <= outDigit(2);
    b     <= outDigit(1);
    a     <= outDigit(0);
    
end architecture Behavioral;