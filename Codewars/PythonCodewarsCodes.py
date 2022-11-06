#In DNA strings, symbols "A" and "T" are complements of each other, as "C" and "G". 
#Your function receives one side of the DNA you need to return the other complementary side. 
def dna_strand(dna):
    return dna.translate(string.maketrans("ATCG","TAGC"))

#vowels count
def vowels_count(sentence):
    return len(list(filter(lambda l : l in ['a', 'e', 'i', 'o', 'u'], sentence)))

#count all number multiple of 3 or 5
def count_multiple(number):
    return sum(filter(lambda n : (n % 3  == 0) or (n%5 == 0), [i for i in range(number)]))

#This kata is about multiplying a given number by eight if it is an even number and by nine otherwise.
def simple_multiplication(number) :
    return (number * 8 if not number & 1 else number * 9)

#Given a string of digits, you should replace any digit below 5 with '0' and any digit 5 and above with '1'. Return the resulting string.
def fake_bin(x):
    return "".join(['0' if (int(n) < 5) else '1' for n in x])


#Complete the solution so that it splits the string into pairs of two characters. 
#If the string contains an odd number of characters then it should replace the missing second character of the final pair with an underscore ('_').
def solution(s):
    result = [tuple[0]+tuple[1] for tuple in list(zip(s[0::2], s[1::2]))]
    return result if (not len(s) & 1) else result + [s[-1] + '_']