#include <algorithm>

#include "print.hpp"
#include "function_to_learn_icn.hpp" // for number_functions

string spec_operation( int number )
{
	switch( number )
	{
	case 0:
		return "";
		break;
	case 1:
		return "HP difference && linear function";
		break;
	case 2:
		return "HP difference && exponential function";
		break;
	case 3:
		return "HP difference && logarithm function";
		break;
	case 4:
		return "HP difference && logstic function";
		break;
	case 5:
		return "";
		break;
	case 6:
		return "distance && linear function";
		break;
	case 7:
		return "distance && exponential function";
		break;
	case 8:
		return "distance && logarithm function";
		break;
	case 9:
		return "distance && logstic function";
		break;
	case 10:
		return "";
		break;
	case 11:
		return "relative speed && linear function";
		break;
	case 12:
		return "relative speed && exponential function";
		break;
	case 13:
		return "relative speed && logarithm function";
		break;
	case 14:
		return "relative speed && logstic function";
		break;
	case 15:
		return "enemy is attacking (positive)";
		break;
	case 16:
		return "enemy is attacking (negative)";
		break;
	case 17:
		return "agent can hit (postive)";
		break;
	case 18:
		return "agent can hit (negative)";
		break;
	case 19:
		return "enemy can hit (postive)";
		break;
	case 20:
		return "enemy can hit (negative)";
		break;
	default:
		return "error1";
	}
}

string comb_operation( int number )
{
	switch( number )
	{
	case 0:
		return "Summation";
		break;
	case 1:
		return "Average";
		break;
	case 2:
		return "Max";
		break;
	case 3:
		return "Min";
		break;
	default:
		return "error2";
	}	
}

string con_operation( int number )
{
	switch( number )
	{
	case 0:
		return "0.25";
		break;
	case 1:
		return "0.5";
		break;
	case 2:
		return "1.0";
		break;
	case 3:
		return "2.0";
		break;
	case 4:
		return "4.0";
		break;
	default:
		return "error3";
	}	
}

void print_model( const Indi& indi )
{
	vector<int> vec( indi.size() );
	std::copy( indi.begin(), indi.end(), vec.begin() );
	print_model( vec );
}
/*
void print_model( const string& indi )
{
	vector<int> vec;
	for( const char& c : indi )
		vec.push_back( stoi( std::string(1, c) ) );
	print_model( vec );
}
*/
void print_model( const vector<int>& indi )
{
	int length=number_units_specification+number_units_combination+number_units_constant;
	for(int i=0;i<number_actions;i++)
	{
		string spec="";
		string comb="";
		string con="";
		for(int j=i*length;j<i*length+number_units_specification;j++)
		{
			if(indi[j]==1 && j!=i*length && j!=i*length+5 && j!=i*length+10) 
				spec=spec+spec_operation(j-i*length)+" + ";
		}
		spec=spec.substr(0,spec.length()-3);
		for(int j=i*length+number_units_specification;j<i*length+number_units_specification+number_units_combination;j++)
		{
			if(indi[j]==1)
			{
				comb=comb_operation(j-i*length-number_units_specification);
				break;
			} 
		}
		for(int j=i*length+number_units_specification+number_units_combination;j<i*length+number_units_specification+number_units_combination+number_units_constant;j++)
		{
			if(indi[j]==1) 
			{
				con=con_operation(j-i*length-number_units_specification-number_units_combination);
				break;
			}
		}
		cout<<"action"<<i<<": "<<con<<"*"<<comb<<"("<<spec<<")"<<endl;
	}
	

	/*
	cout << "\t|\n"
	     << "\tv\n"
	     << agreg << "\n"
	     << "\t|\n"
	     << "\tv\n";

	for( int i = 0; i < number_units_compar; ++i )
		if( indi[i + number_units_transfo + 2 ] )
		{
			cout << compar_operation(i) << "\n";
			break;
		}
	*/
}
