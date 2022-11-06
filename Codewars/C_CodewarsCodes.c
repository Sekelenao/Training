#include <string.h>
#include <stdlib.h>

/* Check if a string is uppercase */
bool is_uppercase(const char *p) {
  while (*p) if (islower(*p++)) return false;
  return true;
}

/*Count the number of '1' bits*/
size_t countBits(unsigned value){
	unsigned int count = 0;
	while(value){
		count += value & 1;
		value >>= 1;
	}
	return count;
}

/*Hide all chars except the 4 last.*/
char *maskify (char *masked, const char *string){
	int i;
	*masked = '\0';
	int length = strlen(string);
	for(i = 0; i < length; i++){
		if(i < length - 4){
			masked[i] = '#';
		}
		else{
			masked[i] = string[i];
		}
	}
	return masked;
}