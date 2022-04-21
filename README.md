# ConnectFour

This is a program that can play Connect Four against a player. To execute the program you need to have java 8 installed on your computer and run the following command:

$> javac *.java && java Main

If you want to insert an input file, the file must be in this format:

*** FILE ***  
(Board width)  
(Board height)  
(Algorithm ID)  
(If you want to start first "yes", else "no")  
(Column you want to play)  
...  
*** FILE ***  

And then run the command:

$> javac *.java && java Main < [filename].[extension]

Algorithm ID:  
1 - MinMax  
2 - Alfa-Beta  
3 - MCTS  

Example of a file:  

*** FILE ***  
7  
6  
3  
yes  
7  
7  
7  
7  
7  
*** FILE ***  
No need for external libraries. The program was compiled with Linux (Fedora v31) using the javac (v1.8.0_272) compiler.
