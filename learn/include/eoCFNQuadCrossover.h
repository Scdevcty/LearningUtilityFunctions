/*
Template for simple quadratic crossover operators
=================================================

Quadratic crossover operators modify the both genotypes
*/

#ifndef eoCFNQuadCrossover_H
#define eoCFNQuadCrossover_H

#include <string>

#if defined DEBUG
#include <iostream>
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
class eoCFNQuadCrossover: public eoQuadOp<GenotypeT>
{
	int _number_units_transfo;
	int _length;
	int _number_actions;

public:
  /**
   * Ctor - no requirement
   */
	eoCFNQuadCrossover( int number_units_transfo, int length, int number_actions)
		: _number_units_transfo( number_units_transfo ),
		 _length( length ),
		 _number_actions( number_actions )
  { }

  /// The class name. Used to display statistics
  string className() const { return "eoCFNQuadCrossover"; }

  /**
   * eoQuad crossover - _genotype1 and _genotype2 are the (future)
   *       offspring, i.e. _copies_ of the parents, to be modified
   * @param _genotype1 The first parent
   * @param _genotype2 The second parent
   */
  bool operator()( GenotypeT& genotype1, GenotypeT& genotype2 )
  {
	  //unsigned site = eo::rng.random( std::min( genotype1.size(), genotype2.size() ) );

	  // cross site to be only at the last transformation unit, or the arithmetic unit,
	  // or the agregation unit, or the first comparison unit.
	  unsigned site = _number_units_transfo ;
	  unsigned i = eo::rng.random( _number_actions );

#if defined DEBUG
	  cout << "Site of crossover: " << site << "\n";
		cout << "Before crossover: ";
		std::copy( genotype1.begin(), genotype1.end(), std::ostream_iterator<int>( cout, " " ) );
		cout << "\n";
		std::copy( genotype2.begin(), genotype2.end(), std::ostream_iterator<int>( cout, " " ) );
		cout << "\n";
#endif
		
		if( !std::equal( genotype1.begin()+i*_length, genotype1.begin() + site+i*_length, genotype2.begin()+i*_length ) )
	  {
		  std::swap_ranges( genotype1.begin()+i*_length, genotype1.begin() + site+i*_length, genotype2.begin()+i*_length );
#if defined DEBUG
		  cout << "After crossover:  ";
		  std::copy( genotype1.begin(), genotype1.end(), std::ostream_iterator<int>( cout, " " ) );
		  cout << "\n";
#endif
		  return true;
	  }
	  return false;
  }
};

#endif
