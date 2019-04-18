# Case study details

This project contains program to find the maximum sum of the numbers as per the given rules below:
 
1.	Start from the top and move downwards to an adjacent number as in below.
2.	Allowed to walk downwards and diagonally.
3.	Walk over the numbers as evens and odds subsequently. Suppose that you are on an even
number the next number you walk must be odd, or if you are stepping over an odd number the next
number must be even. In other words, the final path would be like Odd -> even -> odd -> even
4.	Reach to the bottom of the pyramid.

##Sample Input:
1
8 9
1 5 9
4 5 2 3

##Output:
Max sum: 16
Path: 1, 8, 5, 2 

##Technical stack
Maven 3.X
Java 1.8
Junit 4.x