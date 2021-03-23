#include <string>

#include <eo>
#include <ga.h>

typedef eoBit<eoMinimizingFitness> Indi;

string transfo_operation( int number );
string compar_operation( int number );
void print_model( const Indi& indi,int number_actions );
void print_model( const string& indi,int number_actions );
void print_model( const vector<int>& indi ,int number_actions);

