//-----------------------------------------------------------------------------
// standard includes
#include <stdexcept>  // runtime_error 
#include <iostream>    // cout
#include <sstream>

#include <ctime>
#include <vector>

#include <string>
#include <map>
#include <algorithm>
#include <set>

// Command line arguments manager
#include <argh.h>

// the general include for eo
#include <eo>
#include <ga.h>

#include "function_to_learn_icn.hpp" // for number_functions

#include "randutils.hpp"
#include "print.hpp"

using namespace std;

int nb_vars;
vector<int> random_solutions;
string input_file_path;
ifstream input_file;
string line, string_number;
bool xp;

randutils::mt19937_rng rng_utils;


void usage( char **argv )
{
	cout << "Usage: " << argv[0] << " -n NB_VARIABLES -i INPUT_FILE \n"
	     << "Arguments:\n"
	     << "-h, --help, printing this message.\n"
	     << "-n, --nb_vars NB_VARIABLES, the number of variables .\n"
	     << "-i, --input INPUT_FILE containing sampled solutions.\n"
	     << "--xp to print on the screen results for experiments only.\n";
}

//-----------------------------------------------------------------------------
// define your individuals
typedef eoBit<eoMinimizingFitness> Indi;        // A bitstring with fitness double
//-----------------------------------------------------------------------------

eoMinimizingFitness fitness( const Indi& indi )
{
	double cost = 0.0;
	int length=number_units_specification+number_units_combination+number_units_constant;
	vector<vector<int>>  weights(number_actions,vector<int>(length,0));

	for( int i = 0; i < (int)indi.size(); ++i )
	{
		if( indi[i] )
		{
			weights[i/length][i%length]=1;
		}
	}
	/*
	int action_index[7]={17023,29612,38478,52378,61323,77755,101631};	
	*/
	/*
	int action_index[7]={1934,3151,4059,5320,6154,7760,10211};
	*/
/*
	int action_index[6]={1934,3151,4059,5320,6926,9377};
*/
/*
	int action_index[5]={3874,5809,7415,8676,11127};	
*/
/*
	int action_index[6]={3874.5809,7026,8632,9893,12344};
*/
/*
	int action_index[4]={1934,3540,4801,7252};
*/
/*
	int action_index[5]={1934,3151,4757,6018,8469};
*/
/*
	int action_index[2]={4568,8737};
*/
	int action_index[4]={2302,5559,6315,7665};
	for( int i = 0; i < (int)random_solutions.size(); i += nb_vars )
	{
		vector<double> s(number_actions,0.0);
		for(int j=0;j<number_actions;j++)
		{
			s[j]=g( weights[j], random_solutions, i, nb_vars );
			if(s[j]==__DBL_MAX__)
				return __DBL_MAX__;
		}
		int index=0;
		int cal_index=i/nb_vars;
		for(int j=0;j<number_actions;j++)
		{
			if(cal_index>action_index[j])
				index++;
			else
				break;
		}
		double loss=0.0;
		for(int j=0;j<s.size();j++)
		{
			if(j!=index && s[j]>=s[index])
				loss+=1.0;
		}

		cost += loss;

	}

	// favor simple models
	int number_active_units = std::count( indi.begin(), indi.end(), 1 );
	cost += ( static_cast<double>( number_active_units ) / (int)indi.size() );

	return cost;
}

// fix uncorrectly generated individuals
void fix( Indi& indi )
{
	// for each action
	int length=number_units_specification+number_units_combination+number_units_constant;
	for(int action_num=0;action_num<number_actions;action_num++)
	{
	// you need at least one active transformation unit
	if( std::count( indi.begin()+action_num*length, indi.begin()+action_num*length + number_units_specification, 1 ) == 0 )
	{
		int index = rng_utils.uniform( 0, number_units_specification - 1 );
		indi[ index+action_num*length ] = true;
	}
	// you need exactly one avtive combination unit
	if(std::count( indi.begin()+action_num*length + number_units_specification, indi.begin()+action_num*length + number_units_specification+number_units_combination, 1 ) != 1)		
	{
		// combination layer needs exactly 1 operation, if here are more than 1, 0 for keep the first one, 1 for keep the last one
		int index=rng_utils.uniform( 0, 1);
		bool temp=true;
		if(index==0)
		{
			for(int i=0;i<number_units_combination;i++)
			{
				if(temp==true)
				{
					if(indi[number_units_specification+i+action_num*length]==true)
					{
						temp=false;
						continue;
					}
				}
				else
				{
					indi[number_units_specification+i+action_num*length]=false;
				}
			}
		}
		else
		{
			for(int i=number_units_combination-1;i>=0;i--)
			{
				if(temp==true)
				{
					if(indi[number_units_specification+i+action_num*length]==true)
					{
						temp=false;
						continue;
					}
				}
				else
				{
					indi[number_units_specification+i+action_num*length]=false;
				}
			}
		}
		
	}

	// you need exactly one active constant unit
	if(std::count( indi.begin()+action_num*length + number_units_specification+number_units_combination, indi.begin()+action_num*length + number_units_specification+number_units_combination+number_units_constant, 1 ) != 1)		
	{
		// constant layer needs exactly 1 operation, if here are more than 1, 0 for keep the first one, 1 for keep the last one
		int index=rng_utils.uniform( 0, 1);
		bool temp=true;
		if(index==0)
		{
			for(int i=0;i<number_units_constant;i++)
			{
				if(temp==true)
				{
					if(indi[number_units_specification+number_units_combination+i+action_num*length]==true)
					{
						temp=false;
						continue;
					}
				}
				else
				{
					indi[number_units_specification+number_units_combination+i+action_num*length]=false;
				}
			}
		}
		else
		{
			for(int i=number_units_constant-1;i>=0;i--)
			{
				if(temp==true)
				{
					if(indi[number_units_specification+number_units_combination+i+action_num*length]==true)
					{
						temp=false;
						continue;
					}
				}
				else
				{
					indi[number_units_specification+number_units_combination+i+action_num*length]=false;
				}
			}
		}
			
	}
	
	}
}

//-----------------------------------------------------------------------------
int main_function(int argc, char **argv)
{
	argh::parser cmdl( {  "-n", "--nb_vars", "-i", "--input"} );
	cmdl.parse( argc, argv );
	
	if( cmdl[ { "-h", "--help"} ] )
	{
		usage( argv );
		return EXIT_SUCCESS;
	}

	if( ! cmdl( {"n", "nb_vars"} )   )
	{
		usage( argv );
		return EXIT_FAILURE;
	}

	cmdl( {"n", "nb_vars"}, 9) >> nb_vars;

	if( cmdl[ { "--xp" } ] )
		xp = true;
	else
		xp = false;

	if( cmdl( {"i", "input"} ) )
	{
		if( !xp )
			cout << "Loading data from " << input_file_path << "\n";

		cmdl( {"i", "input"} ) >> input_file_path;
		input_file.open( input_file_path );

		getline( input_file, line );
		stringstream line_stream( line );
		int number_samplings;
		int number;
		line_stream >> number_samplings;

		// loading solutions
		for( int i = 0; i < number_samplings; ++i )
		{
			getline( input_file, line );
			stringstream line_stream( line );
			while( line_stream >> string_number )
			{
				stringstream number_stream( string_number );
				number_stream >> number;
				random_solutions.push_back( number );
			}
		}
		
		input_file.close();
	}
	
	// all parameters are hard-coded!
	const unsigned int SEED = time(0);
	const unsigned int T_SIZE = 2;        // size for tournament selection
	const unsigned int VEC_SIZE = (number_units_specification + number_units_combination + number_units_constant)*number_actions ;    // Number of bits in genotypes
	const unsigned int POP_SIZE = 100;  // Size of population
	const unsigned int STEADY_GEN = 15;  // Number of generations with no improvements before STOP
	const unsigned int MIN_GEN = 200;  // Minimum number of generation before STOP
	const float CROSS_RATE = 0.6;          // Crossover rate
	const float MUT_RATE = 1.0;              // mutation rate
	const float REP_RATE = 0.17;				// replacement rate

	//////////////////////////
	//  Random seed
	//////////////////////////
	//reproducible random seed: if you don't change SEED above, 
	// you'll aways get the same result, NOT a random run
	rng.reseed(SEED);

	if( !xp )
		cout << "Number of variables: " << nb_vars
		     << "\nNumber of solutions: " << random_solutions.size() / nb_vars << endl;


/////////////////
	
	/////////////////////////////
	// Fitness function
	////////////////////////////
	// Evaluation: from a plain C++ fn to an EvalFunc Object
	eoEvalFuncPtr<Indi> eval(  fitness );

	////////////////////////////////
	// Initilisation of population
	////////////////////////////////
	// declare the population
	eoPop<Indi> pop;
	// fill it!
	for( unsigned int igeno = 0; igeno < POP_SIZE; ++igeno )
	{
		Indi v;                    // void individual, to be filled

		for (unsigned int ivar=0; ivar<VEC_SIZE; ivar++)
		{
			bool r = rng.flip(); // new value, random in {0,1}
			v.push_back(r);          // append that random value to v
		}
		fix(v);		
		eval(v);                                // evaluate it
		pop.push_back(v);              // and put it in the population
	}
	// sort pop before printing it!
	pop.sort();
	// Print (sorted) intial population (raw printout)

	
	if( !xp )
		cout << "Initial Population\n" << pop;

	/////////////////////////////////////
	// selection and replacement
	////////////////////////////////////
	// The robust tournament selection
	eoDetTournamentSelect<Indi> selectOne(T_SIZE);            // T_SIZE in [2,POP_SIZE]
	// is now encapsulated in a eoSelectPerc (entage)
	eoSelectPerc<Indi> select( selectOne );// by default rate==1
	eoElitism<Indi> replace( REP_RATE );
	eoDetTournamentTruncate<Indi> tournament(T_SIZE);
	
	//////////////////////////////////////
	// The variation operators
	//////////////////////////////////////
	// 1-point crossover for bitstring
	//test crossover
	eoCFNQuadCrossover<Indi> xover( number_units_specification, number_units_specification+number_units_combination+number_units_constant, number_actions);
	
	// homemade bit-flip mutation for bitstring 
	eoCFNMutation<Indi>  mutationBitFlip( number_units_specification, number_units_combination, number_units_constant, number_actions );
	
	// The operators are  encapsulated into an eoTRansform object
	eoSGATransform<Indi> transform( xover, CROSS_RATE, mutationBitFlip, MUT_RATE );
	
	//////////////////////////////////////
	// termination conditions: use more than one
	/////////////////////////////////////
	// stop after MAX_GEN generations
	//eoGenContinue<Indi> genCont( MAX_GEN );
	//eoCombinedContinue<Indi> continuator( genCont );

	// does a minimum number of generations, then stops whenever a given number of generations takes place without improvement.
	eoSteadyFitContinue<Indi> steadyFit( MIN_GEN, STEADY_GEN); 
	eoCombinedContinue<Indi> continuator( steadyFit );

	/////////////////////////////////////////
	// the algorithm
	////////////////////////////////////////
  // Easy EA requires
  // selection, transformation, eval, replacement, and stopping criterion
	
	eoEasyEA<Indi> gga( continuator, eval, select, transform, replace, tournament );

	// Apply algo to pop - that's it!
	gga(pop);
 
	// Print (sorted) intial population
	pop.sort();
	if( !xp )
		cout << "FINAL Population\n" << pop << "\n";

	//eval(pop[0]);

	auto best_fitness = pop[0].fitness();
	int number_ex_aequo;
	for( number_ex_aequo = 0 ; pop[number_ex_aequo].fitness() == best_fitness ; ++number_ex_aequo ) ; // empty loop

	std::map<std::string,int> count_vectors;
	for( int i = 0; i < number_ex_aequo ; ++i )
	{
		std::ostringstream vector_stream;
		std::copy(pop[i].begin(), pop[i].end(), std::ostream_iterator<bool>(vector_stream, ""));
		++count_vectors[ vector_stream.str() ];
	}

	std::string more_frequent_vector;
	int highest_frequency = 0;
	
	for( auto& m : count_vectors )
		if( highest_frequency < m.second )
		{
			highest_frequency = m.second;
			more_frequent_vector = m.first;
		}

	int index;
	for( index = 0; index < number_ex_aequo ; ++index )
	{
		std::ostringstream vector_stream;
		std::copy(pop[index].begin(), pop[index].end(), std::ostream_iterator<bool>(vector_stream, ""));
		if( vector_stream.str().compare( more_frequent_vector ) == 0 )
			break;
	}

	
	if( !xp )
	{
		//cout << "Best individual11: " << pop[0]
		cout << "Best individual11: " << more_frequent_vector
		     << "\nNumber of variables: " << nb_vars
		     << "\nNumber of solutions: " << random_solutions.size() / nb_vars << endl;
	
		//print_model( pop[0] );
		print_model( pop[index] );
	}
	else
		cout << pop[index] << "\n";
		//cout << pop[0] << "\n";

	return EXIT_SUCCESS;
}
// A main that catches the exceptions
int main(int argc, char **argv)
{
#ifdef _MSC_VER
	//  rng.reseed(42);
	int flag = _CrtSetDbgFlag(_CRTDBG_LEAK_CHECK_DF);
	flag |= _CRTDBG_LEAK_CHECK_DF;
	_CrtSetDbgFlag(flag);
//    _CrtSetBreakAlloc(100);
#endif
	
	try
	{
		return main_function(argc, argv);
	}
	catch(exception& e)
	{
		cout << "Exception: " << e.what() << '\n';
	}
}
