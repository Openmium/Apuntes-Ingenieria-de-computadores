----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12.04.2026 11:36:11
-- Design Name: 
-- Module Name: div1hz - Behavioral
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
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity div1hz is
 port (
    clockInput: in std_logic;
    clk1Hz: out std_logic
 );
end div1hz;

architecture Behavioral of div1hz is
    signal tempClk1Hz: std_logic:= '0';
begin

    process(clockInput)
        variable contador : INTEGER RANGE 1 to 50000000 := 1;
    begin
        if rising_edge(clockInput)then
            if(contador = 50000000) then
                contador:= 1;
                tempClk1Hz <= not tempClk1Hz;
            else
                contador:= contador + 1;
            end if;
        end if;
    end process;
    
    clk1Hz <= tempClk1Hz;

end Behavioral;


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity FF_d is
    port(
        clk, D: in std_logic;
        Q: out std_logic := '0';
        Qn: out std_logic := '1'
    );
end entity FF_d;

architecture FlujoDatos of FF_d is
    signal temp: std_logic := '0';
begin
    process(clk)
        begin
            if(rising_edge(clk))then
                temp <= D;
            end if;
    end process;
    
    Q <= temp;
    Qn <= not temp;
    
end architecture FlujoDatos;

    

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity divisores is
    port(
        clockIn100MHz: in std_logic;
        clockOut1Hz, clockOut05Hz, clockOut025Hz, clockOut0125Hz: out std_logic
    );
end entity divisores;


architecture Structural of divisores is
    signal originalClkIn_S, masterclock1Hz_S: std_logic;
    signal clk0_S, D0_S, Q0_S, Qn0_S: std_logic;
    signal clk1_S, D1_S, Q1_S, Qn1_S: std_logic;
    signal clk2_S, D2_S, Q2_S, Qn2_S: std_logic;
begin
    clk1Hz_use: entity work.div1hz(Behavioral)
        port map(
            clockInput => originalClkIn_S, clk1Hz => masterclock1Hz_S
        );
        
    FF_D0_use: entity work.FF_d(FlujoDatos)
        port map(
            clk => clk0_S, D => D0_S, Q => Q0_S, Qn => Qn0_S
        );
    FF_D1_use: entity work.FF_d(FlujoDatos)
        port map(
            clk => clk1_S, D => D1_S, Q => Q1_S, Qn => Qn1_S
        );
    FF_D2_use: entity work.FF_d(FlujoDatos)
        port map(
            clk => clk2_S, D => D2_S, Q => Q2_S, Qn => Qn2_S
        );
    
    originalClkIn_S <= clockIn100MHz;
    
    D0_S <= Qn0_S;
    D1_S <= Qn1_S;
    D2_S <= Qn2_S;
    
    clk0_S <= masterclock1Hz_S;
    clk1_S <= Q0_S;
    clk2_S <= Q1_S;
    
    
    clockOut1Hz <= masterclock1Hz_S;
    clockOut05Hz <= Q0_S;
    clockOut025Hz <= Q1_S;
    clockOut0125Hz <= Q2_S;
    
end architecture Structural;


