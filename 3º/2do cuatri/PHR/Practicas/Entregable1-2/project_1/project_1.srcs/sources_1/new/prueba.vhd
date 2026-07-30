----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 21.03.2026 14:07:05
-- Design Name: 
-- Module Name: PAL_4L2 - Estructural
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
-- PUERTA AND GENERIC
entity and_N is
    
    generic(
        N : positive := 2
    );
    
    port (
        Inputs : in std_logic_vector(N-1 downto 0);
        S : out std_logic
    );
end and_N;

architecture Flujo_de_Datos of and_N is
    signal temp : std_logic_vector(N-1 downto 0);
begin
    temp(0) <= Inputs(0);

    gen_and : for i in 1 to N-1 generate
        temp(i) <= temp(i-1) and Inputs(i);
    end generate;

    S <= temp(N-1);
    
end Flujo_de_Datos;



library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;   -- necesaria para to_unsigned
-- Testbench
entity test_AND is
end test_AND;

architecture testeandoAnd of test_AND is
    constant N : positive := 8;  
    signal Inputs : std_logic_vector(N-1 downto 0);
    signal S      : std_logic;
begin
    and_use: entity work.and_N(Flujo_de_Datos)
        generic map (N => N)       
        port map (Inputs => Inputs, S => S);

    process
    begin
        for i in 0 to 2**N - 1 loop
            Inputs <= std_logic_vector(to_unsigned(i, N));
            wait for 10 ns;       
        end loop;
        wait;  
    end process;
end testeandoAnd;






library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
-- PUERTA OR GENERIC
entity or_N is
    
    generic(
        N : positive := 2
    );
    
    port (
        Inputs : in std_logic_vector(N-1 downto 0);
        S : out std_logic
    );
end or_N;

architecture Flujo_de_Datos of or_N is
    signal temp : std_logic_vector(N-1 downto 0);
begin
    temp(0) <= Inputs(0);

    gen_and : for i in 1 to N-1 generate
        temp(i) <= temp(i-1) or Inputs(i);
    end generate;

    S <= temp(N-1);
    
end Flujo_de_Datos;



library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL; 

-- Testbench
entity test_OR is
end test_OR;

architecture testeandoOr of test_OR is
    constant N : positive := 8;  
    signal Inputs : std_logic_vector(N-1 downto 0);
    signal S      : std_logic;
begin
    or_use: entity work.or_N(Flujo_de_Datos)
        generic map (N => N)       
        port map (Inputs => Inputs, S => S);

    process
    begin
        for i in 0 to 2**N - 1 loop
            Inputs <= std_logic_vector(to_unsigned(i, N));
            wait for 10 ns;       
        end loop;
        wait;  
    end process;
end testeandoOr;





library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
-- PUERTA TRIESTADO
entity triestado is 
    port(
        data_in  : in  std_logic;
        enable   : in  std_logic;
        data_out : out std_logic
    );
end triestado;

architecture Comportamiento of triestado is
begin
    data_out <= data_in when enable = '1' else 'Z';
end Comportamiento;



library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
-- Testbench
entity test_Triestado is
end test_Triestado;

architecture testeandoTriestado of test_Triestado is

signal input, enable: std_logic;
signal output : std_logic;

begin
    triestado_use: entity  work.triestado(Comportamiento)
    port map(
        data_in => input,
        enable => enable,
        data_out => output
    );
    
    enable <= '0' after 0 ns, '1' after 20 ns;
    input <= '0' after 0 ns, '1' after 10 ns, '0' after 20 ns, '1' after 30 ns;
    
end testeandoTriestado;











library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
-- PAL_4L2
entity PAL_4L2 is

generic(
    -- 1 fusible intacto / 0 fusible roto
         fusibles5: std_logic_vector(7 downto 0):= "00000000"; 
         fusibles4: std_logic_vector(7 downto 0):= "00000000"; 
         fusibles3: std_logic_vector(7 downto 0):= "00110000"; -- control triestado 1
         fusibles2: std_logic_vector(7 downto 0):= "00000000";
         fusibles1: std_logic_vector(7 downto 0):= "00000000";
         fusibles0: std_logic_vector(7 downto 0):= "00110000" -- control triestado 0
);

    port(
        A: in std_logic;
        B: in std_logic;
        C: in std_logic;
        Y_out: out std_logic;
        in_out: inout std_logic
    );
end PAL_4L2;

architecture Estructural of PAL_4L2 is

signal entrada: std_logic_vector(7 downto 0);

    constant N_AND: positive := 8;
    constant N_OR: positive := 2;
    
    signal and_in5, and_in4, and_in3, and_in2, and_in1, and_in0 : std_logic_vector(N_AND -1 downto 0);
    signal and_out5, and_out4, and_out3, and_out2, and_out1, and_out0 : std_logic;
    
    
    signal or_in1, or_in0: std_logic_vector(N_OR -1 downto 0);
    signal or_out1, or_out0: std_logic;
    

    signal triestado_in1, enable1, triestado_out1, triestado_in0, enable0, triestado_out0: std_logic;
    
    

begin

    entrada <= in_out & not(in_out) & A & not(A) & B & not(B) & C & not(C);
    
    and_in5 <= entrada or (not fusibles5);
    and_in4 <= entrada or (not fusibles4);
    and_in3 <= entrada or (not fusibles3);
    and_in2 <= entrada or (not fusibles2);
    and_in1 <= entrada or (not fusibles1);
    and_in0 <= entrada or (not fusibles0);

AND_N5: entity work.and_N(Flujo_de_Datos)
        generic map (N => N_AND)
        port map (Inputs => and_in5, S => and_out5);

AND_N4: entity work.and_N(Flujo_de_Datos)
        generic map (N => N_AND)
        port map (Inputs => and_in4, S => and_out4);

AND_N3_triestado: entity work.and_N(Flujo_de_Datos)
        generic map (N => N_AND)
        port map (Inputs => and_in3, S => and_out3);
        
        
        
AND_N2: entity work.and_N(Flujo_de_Datos)
        generic map (N => N_AND)
        port map (Inputs => and_in2, S => and_out2);
        
AND_N1: entity work.and_N(Flujo_de_Datos)
        generic map (N => N_AND)
        port map (Inputs => and_in1, S => and_out1);
        
AND_N0_triestado: entity work.and_N(Flujo_de_Datos)
        generic map (N => N_AND)
        port map (Inputs => and_in0, S => and_out0);
    
    
    or_in1 <= and_out5 & and_out4;
    or_in0 <= and_out2 & and_out1;
    
OR_N1: entity work.or_N(Flujo_de_Datos)
        generic map (N => N_OR)
        port map (Inputs => or_in1, S => or_out1);
        
OR_N0: entity work.or_N(Flujo_de_Datos)
        generic map (N => N_OR)
        port map (Inputs => or_in0, S => or_out0);

    triestado_in1 <= not or_out1; -- Salida logica Low
    enable1 <= and_out3;
    
    triestado_in0 <= not or_out0; -- Salida logica Low
    enable0 <= and_out0;
        
triestado_1: entity work.triestado(Comportamiento)
        port map(
            data_in => triestado_in1,
            enable => enable1,
            data_out => triestado_out1
        );
        
triestado_0: entity work.triestado(Comportamiento)
        port map(
            data_in => triestado_in0,
            enable => enable0,
            data_out => triestado_out0
        );

    
     Y_out <= triestado_out1;
     in_out <= triestado_out0;
     

end Estructural;



library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
--PAL PROG como mux de dos canales
entity PAL_Prog is
    port(
        D0: in  std_logic;
        D1: in  std_logic;
        Sel: in  std_logic;
        Y: out std_logic
    );
end PAL_Prog;

architecture Implementacion_MUX of PAL_Prog is

signal sin_usar: std_logic:= 'Z';

    constant F5: std_logic_vector(7 downto 0) := "00010010";
    constant F4: std_logic_vector(7 downto 0) := "00101000";
    constant F3: std_logic_vector(7 downto 0) := "00000000";
    constant F2: std_logic_vector(7 downto 0) := "00000000";
    constant F1: std_logic_vector(7 downto 0) := "00000000";
    constant F0: std_logic_vector(7 downto 0) := "00000000";

begin
    PAL_4L2_use: entity work.PAL_4L2(Estructural)
        generic map(fusibles5 => F5,fusibles4 => F4, fusibles3 => F3, fusibles2 => F2, fusibles1 => F1, fusibles0 => F0)
        port map(in_out => sin_usar, A => Sel, B => D1, C => D0, Y_out => Y);
       
end Implementacion_MUX;



library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
-- PAL_test_inout 
entity PAL_test_inout is
    port(
        A: in  std_logic;
        B: in  std_logic;
        C: in  std_logic;
        input_output: inout std_logic:= 'Z';
        Y: out std_logic
    );
end PAL_test_inout;

architecture Implementacion_inout of PAL_test_inout is


    constant F5: std_logic_vector(7 downto 0) := "10000000";
    constant F4: std_logic_vector(7 downto 0) := "00110000";
    constant F3: std_logic_vector(7 downto 0) := "00000000";
    constant F2: std_logic_vector(7 downto 0) := "00110000";
    constant F1: std_logic_vector(7 downto 0) := "00110000";
    constant F0: std_logic_vector(7 downto 0) := "00110000";

begin
    PAL_4L2_use: entity work.PAL_4L2(Estructural)
        generic map(fusibles5 => F5,fusibles4 => F4, fusibles3 => F3, fusibles2 => F2, fusibles1 => F1, fusibles0 => F0)
        port map(in_out => input_output, A => A, B => B, C => C, Y_out => Y);
       
end Implementacion_inout;


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
-- PAL_test_realimentacion
entity PAL_test_realimentacion is
    port(
        A: in  std_logic;
        B: in  std_logic;
        C: in  std_logic;
        input_output: inout std_logic:= 'Z';
        Y: out std_logic
    );
end PAL_test_realimentacion;

architecture Implementacion_realimentacion of PAL_test_realimentacion is


    constant F5: std_logic_vector(7 downto 0) := "10000010";
    constant F4: std_logic_vector(7 downto 0) := "00110000";
    constant F3: std_logic_vector(7 downto 0) := "00000000";
    constant F2: std_logic_vector(7 downto 0) := "00100000";
    constant F1: std_logic_vector(7 downto 0) := "00001000";
    constant F0: std_logic_vector(7 downto 0) := "00000000";

begin
    PAL_4L2_use: entity work.PAL_4L2(Estructural)
        generic map(fusibles5 => F5,fusibles4 => F4, fusibles3 => F3, fusibles2 => F2, fusibles1 => F1, fusibles0 => F0)
        port map(in_out => input_output, A => A, B => B, C => C, Y_out => Y);
       
end Implementacion_realimentacion;






library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
-- Testbench
entity test_PAL_MUX is
end test_PAL_MUX;

architecture MUX_PAL of test_PAL_MUX is

signal Sel, D1, D0, output: std_logic;

begin
    PAL_use: entity  work.PAL_Prog(Implementacion_MUX)
    port map(
        Sel => Sel,
        D1 => D1,
        D0 => D0,
        Y => output
    );
    
    Sel <= '0' after 0ns, '1' after 40ns;
    D1 <= '0' after 0ns, '1' after 20ns, '0' after 40ns, '1' after 60ns;
    D0 <= '0' after 0ns, '1' after 10ns, '0' after 20 ns, '1' after 30ns, '0' after 40ns, '1' after 50ns, '0' after 60 ns, '1' after 70ns;
    
end MUX_PAL;


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
-- Testbench
entity test_PAL_inout is
end test_PAL_inout;

architecture testeando_inout_PAL of test_PAL_inout is

signal A, B, C, input_output, output: std_logic;

begin
    PAL_use: entity  work.PAL_test_inout(Implementacion_inout)
    port map(
        A => A,
        B => B,
        C => C,
        input_output => input_output,
        Y => output
    );
    
     A <= '0' after 0 ns, '1' after 40 ns;
    B <= '0' after 0 ns, '1' after 20 ns, '0' after 40 ns, '1' after 60 ns;
    C <= '0' after 0 ns, '1' after 10 ns, '0' after 20 ns, '1' after 30 ns, '0' after 40 ns, '1' after 50 ns, '0' after 60 ns, '1' after 70 ns;
    
    input_output <= '0' after 0 ns, '1' after 15 ns, '0' after 35 ns, '1' after 55 ns;
    
end testeando_inout_PAL;


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
-- Testbench
entity test_PAL_realimentacion is
end test_PAL_realimentacion;

architecture testeando_realimentacion_PAL of test_PAL_realimentacion is

signal A, B, C, input_output, output: std_logic;

begin
    PAL_use: entity  work.PAL_test_realimentacion(Implementacion_realimentacion)
    port map(
        A => A,
        B => B,
        C => C,
        input_output => input_output,
        Y => output
    );
    
    A <= '0' after 0 ns, '1' after 40 ns;
    B <= '0' after 0 ns, '1' after 20 ns, '0' after 40 ns, '1' after 60 ns;
    C <= '0' after 0 ns, '1' after 10 ns, '0' after 20 ns, '1' after 30 ns, '0' after 40 ns, '1' after 50 ns, '0' after 60 ns, '1' after 70 ns;
    
end testeando_realimentacion_PAL;
