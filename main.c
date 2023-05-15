#include <stdio.h>
#include <stdlib.h>

int** my2DAlloc (int i, int j){
    int** matrix = (malloc (sizeof(int*) * i));
    for(int a = 0; a < j; a++){
        *(matrix + a) = malloc(sizeof(int) * j);
    }
    return matrix;
}
void printAndFree2DMatrix(int** matrix, int i, int j){
    int num = 1;
    //filling the matrix with numbers 1 to (i*j)
    for(int g = 0; g < i; g++){
        for(int h = 0; h < j; h++){
            *(*(matrix + g) + h) = num++;
        }
    }
    //printing the values in the matrix
    for(int b = 0; b < i; b++){
        for(int c = 0; c < j; c++){
            printf("%d ", *(*(matrix + b) + c));
        }
        printf("\n");
    }
    //freeing the space taken by the matrix
    for(int d = 0; d < i; d++){
        free(*(matrix + d));
    }
    //after freeing the memory taken by the whole matrix, the values previously entered are erased:
    for(int b = 0; b < i; b++){
        for(int c = 0; c < j; c++){
            printf("%d ", *(*(matrix + b) + c));
        }
        printf("\n");
    }
}
int main()
{
    printAndFree2DMatrix(my2DAlloc(3,4), 3, 4);
    return 0;
}
