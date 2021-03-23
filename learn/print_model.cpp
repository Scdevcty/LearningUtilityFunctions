#include <string>

#include "print.hpp"

int main( int argc, char** argv )
{
	print_model( std::string( argv[1] ) ,int number_actions);
	
	return EXIT_SUCCESS;
}
