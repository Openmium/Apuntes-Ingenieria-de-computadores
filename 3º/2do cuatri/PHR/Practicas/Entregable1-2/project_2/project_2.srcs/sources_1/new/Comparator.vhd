----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 23.03.2026 13:10:35
-- Design Name: 
-- Module Name: inversor - Flujo_de_Datos
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


-- NOT
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity inversor is
    port(entry: in std_logic; output: out std_logic);
end inversor;

architecture Flujo_de_Datos of inversor is
begin
    output <= not entry;
end Flujo_de_Datos;


-- testbench NOT
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity test_inversor is
end test_inversor;

architecture testeandoNOT of test_inversor is
    signal A, Y: std_logic;
begin
    NOT_use: entity work.inversor(Flujo_de_Datos)
        port map(entry => A, output => Y);
        
    A <= '0' after 0 ns, '1' after 10 ns, '0' after 20 ns, '1' after 30 ns;
    
end testeandoNOT;



-- AND
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity producto is
    port(A, B: in std_logic; output: out std_logic);
end producto;

architecture Flujo_de_Datos of producto is
begin
    output <= A and B;
end Flujo_de_Datos;


-- testbench AND
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity test_producto is
end test_producto;

architecture testeandoAND of test_producto is
    signal A, B, Y: std_logic;
begin
    NOT_use: entity work.producto(Flujo_de_Datos)
        port map(A => A, B => B, output => Y);
        
    A <= '0' after 0 ns, '1' after 20 ns;
    B <= '0' after 0 ns, '1' after 10 ns, '0' after 20 ns, '1' after 30 ns;
    
end testeandoAND;


-- OR
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity suma is
    port(A, B: in std_logic; output: out std_logic);
end suma;

architecture Flujo_de_Datos of suma is
begin
    output <= A or B;
end Flujo_de_Datos;

-- testbench OR
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity test_suma is
end test_suma;

architecture testeandoOR of test_suma is
    signal A, B, Y: std_logic;
begin
    NOT_use: entity work.suma(Flujo_de_Datos)
        port map(A => A, B => B, output => Y);
        
    A <= '0' after 0 ns, '1' after 20 ns;
    B <= '0' after 0 ns, '1' after 10 ns, '0' after 20 ns, '1' after 30 ns;
    
end testeandoOR;








-- Comparador 1 bit
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity comparador_1bit is
    port(A, B: in std_logic; g, e, l: out std_logic);
end comparador_1bit;

architecture Estructural of comparador_1bit is
    signal inputA_NOT, outputA_NOT, inputB_NOT, outputB_NOT: std_logic;
    signal A_AND, notA_AND, B_AND, notB_AND, output_AxnotB_AND, output_notAxB_AND, output_notAxnotB_AND, output_AxB_AND: std_logic;
    signal notAxnotB, AxB, output_OR: std_logic;
        
begin
    notA_use: entity work.inversor(Flujo_de_Datos)
        port map(entry => inputA_NOT, output => outputA_NOT);
    notB_use: entity work.inversor(Flujo_de_Datos)
        port map(entry => inputB_NOT, output => outputB_NOT);
        
    andAxnotB_use: entity work.producto(Flujo_de_Datos)
        port map(A => A_AND, B => notB_AND, output => output_AxnotB_AND);
    andnotAxB_use: entity work.producto(Flujo_de_Datos)
        port map(A => notA_AND, B => B_AND, output => output_notAxB_AND);
    andnotAxnotB_use: entity work.producto(Flujo_de_Datos)
        port map(A => notA_AND, B => notB_AND, output => output_notAxnotB_AND);
    andAxB_use: entity work.producto(Flujo_de_Datos)
        port map(A => A_AND, B => B_AND, output => output_AxB_AND);
        
    or_use: entity work.suma(Flujo_de_Datos)
        port map(A => notAxnotB, B => AxB, output => output_OR);
    
    A_AND <= A;
    B_AND <= B;
    
    inputA_NOT <= A;
    inputB_NOT <= B;
    
    notA_AND <= outputA_NOT;
    notB_AND <= outputB_NOT;
    
    AxB <= output_AxB_AND;
    notAxnotB <= output_notAxnotB_AND;
    
    g <= output_AxnotB_AND;
    e <= output_OR;
    l <= output_notAxB_AND;
    
end Estructural;

-- testbench COMPARATOR1b
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity test_comparador_1bit is
end test_comparador_1bit;

architecture testeandoCOMPARATOR of test_comparador_1bit is
    signal A, B, G, E, L: std_logic;
begin
    COMPARATOR_use: entity work.comparador_1bit(Estructural)
        port map(A => A, B => B, g => G, e => E, l => L);
        
    A <= '0' after 0 ns, '1' after 20 ns;
    B <= '0' after 0 ns, '1' after 10 ns, '0' after 20 ns, '1' after 30 ns;
    
end testeandoCOMPARATOR;



-- Expansor
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity expansor is
    port(g_in, e_in, l_in, G1_in, E1_in, L1_in: in std_logic; G_out, E_out, L_out: out std_logic);
end expansor;

architecture Estructural of expansor is
    signal output_exG1_AND, output_exE1_AND, output_exL1_AND: std_logic;

    signal exG1, exL1, output_exG1_OR,output_exL1_OR : std_logic;
    
begin
    and_exG1_use: entity work.producto(Flujo_de_Datos)
        port map(A => e_in, B => G1_in, output => output_exG1_AND);
    and_exE1_use: entity work.producto(Flujo_de_Datos)
        port map(A => e_in, B => E1_in, output => output_exE1_AND);
    and_exL1_use: entity work.producto(Flujo_de_Datos)
        port map(A => e_in, B => L1_in, output => output_exL1_AND);
        
    or_exG1_use: entity work.suma(Flujo_de_Datos)
        port map(A => g_in, B => exG1, output => output_exG1_OR);
    or_exL1_use: entity work.suma(Flujo_de_Datos)
        port map(A => l_in, B => exL1, output => output_exL1_OR);
    
    exG1 <= output_exG1_AND;
    exL1 <= output_exL1_AND;
    
    G_out <= output_exG1_OR;
    E_out <= output_exE1_AND;
    L_out <= output_exL1_OR;
       
end Estructural;


-- testbench EXPANSOR
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity test_expansor is
end test_expansor;

architecture testeandoEXPANSOR of test_expansor is
    signal g_in, e_in, l_in, G1_in, E1_in, L1_in, G, E, L: std_logic;
begin
    COMPARATOR_use: entity work.expansor(Estructural)
        port map(g_in => g_in, e_in => e_in, l_in => l_in, G1_in => G1_in, E1_in => E1_in, L1_in => L1_in, G_out => G, E_out => E, L_out => L);
        
     process
    begin
        
        for i in 0 to 3 loop
            
            if i = 0 then    g_in <= '0'; e_in <= '0'; l_in <= '0';
            elsif i = 1 then g_in <= '1'; e_in <= '0'; l_in <= '0';
            elsif i = 2 then g_in <= '0'; e_in <= '1'; l_in <= '0';
            else             g_in <= '0'; e_in <= '0'; l_in <= '1';
            end if;

            
            for j in 0 to 3 loop
                
                if j = 0 then    G1_in <= '0'; E1_in <= '0'; L1_in <= '0';
                elsif j = 1 then G1_in <= '1'; E1_in <= '0'; L1_in <= '0';
                elsif j = 2 then G1_in <= '0'; E1_in <= '1'; L1_in <= '0';
                else             G1_in <= '0'; E1_in <= '0'; L1_in <= '1';
                end if;
                
                wait for 10 ns;
            end loop;
            
        end loop;
        
        wait; 
    end process;
    
end testeandoEXPANSOR;



-- Comparador 1 BIT con expansor
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity comparador_1bit_expansor is
    port(A, B, G1_in, E1_in, L1_in: in std_logic; G_out, E_out, L_out: out std_logic);
end comparador_1bit_expansor;

architecture Estructural of comparador_1bit_expansor is
    signal g, e, l: std_logic;
begin
    comparador1b_use: entity work.comparador_1bit(Estructural)
        port map(A => A, B => B, g => g, e => e, l => l);
    
    expansor_use: entity work.expansor(Estructural)
        port map(
        g_in => g, 
        e_in => e, 
        l_in => l, 
        G1_in => G1_in, 
        E1_in => E1_in, 
        L1_in => L1_in, 
        G_out => G_out, 
        E_out => E_out, 
        L_out => L_out
        );
        
end Estructural;



-- testbench COMPARATOR GEL 1b 
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity test_comparador_gel_1bit is
end test_comparador_gel_1bit;

architecture testeandoCOMPARATOR_GEL_1bit of test_comparador_gel_1bit is
    signal A, B, G1_in, E1_in, L1_in, G_out, E_out, L_out: std_logic;
begin
    COMPARATOR_use: entity work.comparador_1bit_expansor(Estructural)
        port map(A => A, B => B, G1_in => G1_in, E1_in => E1_in, L1_in => L1_in, G_out => G_out, E_out => E_out, L_out => L_out);
        
   process
    begin
        
        for i in 0 to 3 loop
            
            if i = 0 then    G1_in <= '0'; E1_in <= '0'; L1_in <= '0';
            elsif i = 1 then G1_in <= '1'; E1_in <= '0'; L1_in <= '0';
            elsif i = 2 then G1_in <= '0'; E1_in <= '1'; L1_in <= '0';
            else             G1_in <= '0'; E1_in <= '0'; L1_in <= '1';
            end if;
            
            for j in 0 to 3 loop
                
                if j = 0 then    A <= '0'; B <= '0';
                elsif j = 1 then A <= '0'; B <= '1';
                elsif j = 2 then A <= '1'; B <= '0';
                else             A <= '1'; B <= '1';
                end if;
                
                wait for 10 ns; 
            end loop;
            
        end loop;
        
        wait;
    end process;
    
end testeandoCOMPARATOR_GEL_1bit;




-- Comparador 4 BIT con expansor
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity comparador_4bit_expansor is
    port(A3, A2, A1, A0, B3, B2, B1, B0, G1_in, E1_in, L1_in: in std_logic; G_out, E_out, L_out: out std_logic);
end comparador_4bit_expansor;

architecture Estructural of comparador_4bit_expansor is
    signal G_out_2, E_out_2, L_out_2, G_out_1, E_out_1, L_out_1, G_out_0, E_out_0, L_out_0: std_logic;  
begin
    comparador_1b_GEL_3: entity work.comparador_1bit_expansor(Estructural)
        port map(A => A3, B => B3, G1_in => G_out_2, E1_in => E_out_2, L1_in => L_out_2, G_out => G_out, E_out => E_out, L_out => L_out);
    comparador_1b_GEL_2: entity work.comparador_1bit_expansor(Estructural)
        port map(A => A2, B => B2, G1_in => G_out_1, E1_in => E_out_1, L1_in => L_out_1, G_out => G_out_2, E_out => E_out_2, L_out => L_out_2);
    comparador_1b_GEL_1: entity work.comparador_1bit_expansor(Estructural)
        port map(A => A1, B => B1, G1_in => G_out_0, E1_in => E_out_0, L1_in => L_out_0, G_out => G_out_1, E_out => E_out_1, L_out => L_out_1);
    comparador_1b_GEL_0: entity work.comparador_1bit_expansor(Estructural)
        port map(A => A0, B => B0, G1_in => G1_in, E1_in => E1_in, L1_in => L1_in, G_out => G_out_0, E_out => E_out_0, L_out => L_out_0);

end Estructural;


-- testbench COMPARATOR GEL 4b 
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL; 

entity test_comparador_gel_4bit is
end test_comparador_gel_4bit;

architecture testeandoCOMPARATOR_GEL_4bit of test_comparador_gel_4bit is
    signal A3, A2, A1, A0, B3, B2, B1, B0, G1_in, E1_in, L1_in: std_logic;
    signal G_out, E_out, L_out : std_logic;
begin
   COMPARATOR_GEL_4bit_use: entity work.comparador_4bit_expansor(Estructural)
        port map(
            A3 => A3, A2 => A2, A1 => A1, A0 => A0,
            B3 => B3, B2 => B2, B1 => B1, B0 => B0,
            G1_in => G1_in, E1_in => E1_in, L1_in => L1_in,
            G_out => G_out, E_out => E_out, L_out => L_out
        );
    
    process
        variable temp_A : unsigned(3 downto 0);
        variable temp_B : unsigned(3 downto 0);
    begin
        G1_in <= '0';
        E1_in <= '1';
        L1_in <= '0';

        for i in 0 to 15 loop
            temp_A := to_unsigned(i, 4);
            
            A3 <= temp_A(3);
            A2 <= temp_A(2);
            A1 <= temp_A(1);
            A0 <= temp_A(0);
            
            for j in 0 to 15 loop
                temp_B := to_unsigned(j, 4); 
                
                B3 <= temp_B(3);
                B2 <= temp_B(2);
                B1 <= temp_B(1);
                B0 <= temp_B(0);
                
                wait for 10 ns; 
            end loop;
        end loop;
        
        wait; 
    end process;
    
end testeandoCOMPARATOR_GEL_4bit;

