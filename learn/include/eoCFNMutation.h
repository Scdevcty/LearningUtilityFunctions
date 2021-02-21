/*
  Template for simple mutation operators
  ======================================
*/

#ifndef eoCFNMutation_H
#define eoCFNMutation_H

#include <algorithm>
#include <string>

#if defined DEBUG
#include <iostream>
#include <iterator>
#endif

#include <eoOp.h>

using namespace std;

/**
 *  Always write a comment in this format before class definition
 *  if you want the class to be documented by Doxygen
 *
 * THere is NO ASSUMPTION on the class GenoypeT.
 * In particular, it does not need to derive from EO
 */
template<class GenotypeT>
class eoCFNMutation : public eoMonOp<GenotypeT>
{
	int _number_units_transfo;
	int _number_units_combination;
	int _number_units_constant;
	int _number_actions;
	double _rate;
	bool _normalize;

public:
	/**
	 * Ctor - no requirement
	 */
	eoCFNMutation( int number_units_transfo, int number_units_combination,int number_units_constant, int number_actions)
		: _number_units_transfo( number_units_transfo ),
		 _number_units_combination( number_units_combination ),
		 _number_units_constant(number_units_constant),
		 _number_actions( number_actions )
	{}

	/// The class name. Used to display statistics
	string className() const { return "eoCFNMutation"; }

	/**
	 * modifies the parent
	 * @param genotype The parent genotype (will be modified)
	 */
	bool operator()( GenotypeT& genotype )
	{
		bool changed_something = false;
		int length=_number_units_transfo+_number_units_combination+_number_units_constant;
		unsigned j = eo::rng.random( _number_actions );
		auto active_transfo_units = std::count( genotype.begin()+j*length, genotype.begin() + _number_units_transfo+j*length, true );
/*
		active_transfo_units=active_transfo_units-((genotype[j*length]==true)?1:0)-((genotype[j*length+5]==true)?1:0)-((genotype[j*length+10]==true)?1:0);
*/
		//auto active_spec_units = std::count( std::prev( genotype.end(), _number_units_spec ), genotype.end(), true );

#if defined DEBUG
		cout << "Before flipping: ";
		std::copy( genotype.begin(), genotype.end(), std::ostream_iterator<int>( cout, " " ) );
		cout << "\n";
#endif
	
		unsigned i;

		// Mutate one bit only
		// Forbid flipping the unique active unit in the transformation layer. 
/*
		do
		{
			i = eo::rng.random( length );
		} while( i!=0 && i!=5 && i!=10 && i < _number_units_transfo && active_transfo_units == 1 && genotype[i+length*j] );
*/		
		do
		{
			i = eo::rng.random( length );
		} while(  i < _number_units_transfo && active_transfo_units == 1 && genotype[i+length*j] );
		// If we are about to flip a comparison unit, switch all of them to false to have a unique active unit in this layer.
		if(i>=_number_units_transfo && i<_number_units_transfo+_number_units_combination)
		{
			for(int m=_number_units_transfo+j*length;m<_number_units_transfo+_number_units_combination+j*length;m++)
				genotype[m]=false;
		}

		if(i>=_number_units_transfo+_number_units_combination && i<_number_units_transfo+_number_units_combination+_number_units_constant)
		{
			for(int m=_number_units_transfo+_number_units_combination+j*length;m<_number_units_transfo+_number_units_combination+_number_units_constant+j*length;m++)
				genotype[m]=false;
		}

		genotype[i] = !genotype[i];
		changed_something = true;

		
#if defined DEBUG
		if( changed_something )
		{
			cout << "After flipping:  ";
			std::copy( genotype.begin(), genotype.end(), std::ostream_iterator<int>( cout, " " ) );
			cout << "\n";
		}
#endif
		
		return changed_something;
	}
};

#endif
