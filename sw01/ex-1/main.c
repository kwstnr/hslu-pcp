#include <stdio.h>
#include <stdlib.h>

void printNumbersGoto(int number) {
  int i = 0;
  start:
  printf("%i ", i);
  i++;
  if (i <= number) goto start;
}

void printNumbersFor(int number) {
  for (int i = 0; i <= number; i++) {
    printf("%i ", i);
  }
}

int main(int argc, char** argv) {
  printNumbersGoto(7);
  printf("= printNumbersGoto(7)\n");
  printNumbersFor(7);
  printf("= printNumbersFor(7)\n");
} 
