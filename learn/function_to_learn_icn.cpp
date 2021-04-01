#include <cmath>
#include <complex>
#include <algorithm>
#include <numeric>
#include <limits>

// #include <iostream>
// #include <iterator>

#include "function_to_learn_icn.hpp"

void interpreter_specification( const int& number,
                                 const vector<double>& inputs,
                                 int index_active_unit,
                                 vector<double>& outputs)
{
	switch( number )
	{

	case 0:
	{
		double hp_difference=((inputs[0]-inputs[1])/400.0+1)/2;
		hp_difference=((hp_difference>1)?1.0:hp_difference);
		hp_difference=((hp_difference<0)?0.0:hp_difference);
		outputs[index_active_unit]=-hp_difference;
	}
		break;
	case 1:
	{
		double hp_difference=((inputs[0]-inputs[1])/400.0+1)/2;
		hp_difference=((hp_difference>1)?1.0:hp_difference);
		hp_difference=((hp_difference<0)?0.0:hp_difference);
		outputs[index_active_unit]=hp_difference;
	}
		break;	
	case 2:	
	{
		double hp_difference=((inputs[0]-inputs[1])/400.0+1)/2;
		hp_difference=((hp_difference>1)?1.0:hp_difference);
		hp_difference=((hp_difference<0)?0.0:hp_difference);
		outputs[index_active_unit]=-std::pow(2.7,hp_difference)/2;
	}
		break;	
	case 3:	
	{
		double hp_difference=((inputs[0]-inputs[1])/400.0+1)/2;
		hp_difference=((hp_difference>1)?1.0:hp_difference);
		hp_difference=((hp_difference<0)?0.0:hp_difference);
		outputs[index_active_unit]=std::pow(2.7,hp_difference)/2;
	}
		break;
	case 4:	
	{
		double hp_difference=((inputs[0]-inputs[1])/400.0+1)/2;
		hp_difference=((hp_difference>1)?1.0:hp_difference);
		hp_difference=((hp_difference<0)?0.0:hp_difference);
		outputs[index_active_unit]=-std::log(hp_difference+1);
	}
		break;
	case 5:	
	{
		double hp_difference=((inputs[0]-inputs[1])/400.0+1)/2;
		hp_difference=((hp_difference>1)?1.0:hp_difference);
		hp_difference=((hp_difference<0)?0.0:hp_difference);
		outputs[index_active_unit]=std::log(hp_difference+1);
	}
		break;
	case 6:	
	{
		double hp_difference=((inputs[0]-inputs[1])/400.0+1)/2;
		hp_difference=((hp_difference>1)?1.0:hp_difference);
		hp_difference=((hp_difference<0)?0.0:hp_difference);
		outputs[index_active_unit]=(-1/(1+std::pow(2.7,-1*(hp_difference-0.5))));
	}
		break;
	case 7:	
	{
		double hp_difference=((inputs[0]-inputs[1])/400.0+1)/2;
		hp_difference=((hp_difference>1)?1.0:hp_difference);
		hp_difference=((hp_difference<0)?0.0:hp_difference);
		outputs[index_active_unit]=(1/(1+std::pow(2.7,-1*(hp_difference-0.5))));
	}
		break;
	case 8:
	{
		double distance=std::abs(inputs[2]-inputs[3])/920.0;
		distance=((distance>1)?1.0:distance);
		distance=((distance<0)?0.0:distance);
		outputs[index_active_unit]=-distance;
	}
		break;
	case 9:
	{
		double distance=std::abs(inputs[2]-inputs[3])/920.0;
		distance=((distance>1)?1.0:distance);
		distance=((distance<0)?0.0:distance);
		outputs[index_active_unit]=distance;
	}
		break;
	case 10:
	{
		double distance=std::abs(inputs[2]-inputs[3])/920.0;
		distance=((distance>1)?1.0:distance);
		distance=((distance<0)?0.0:distance);
		outputs[index_active_unit]=-std::pow(2.7,distance)/2;
	}
		break;
	case 11:
	{
		double distance=std::abs(inputs[2]-inputs[3])/920.0;
		distance=((distance>1)?1.0:distance);
		distance=((distance<0)?0.0:distance);
		outputs[index_active_unit]=std::pow(2.7,distance)/2;
	}
		break;
	case 12:
	{
		double distance=std::abs(inputs[2]-inputs[3])/920.0;
		distance=((distance>1)?1.0:distance);
		distance=((distance<0)?0.0:distance);
		outputs[index_active_unit]=-std::log(distance+1);
	}
		break;
	case 13:
	{
		double distance=std::abs(inputs[2]-inputs[3])/920.0;
		distance=((distance>1)?1.0:distance);
		distance=((distance<0)?0.0:distance);
		outputs[index_active_unit]=std::log(distance+1);
	}
		break;
	case 14:
	{
		double distance=std::abs(inputs[2]-inputs[3])/920.0;
		distance=((distance>1)?1.0:distance);
		distance=((distance<0)?0.0:distance);
		outputs[index_active_unit]=(-1/(1+std::pow(2.7,-1*(distance-0.5))));
	}
		break;
	case 15:
	{
		double distance=std::abs(inputs[2]-inputs[3])/920.0;
		distance=((distance>1)?1.0:distance);
		distance=((distance<0)?0.0:distance);
		outputs[index_active_unit]=(1/(1+std::pow(2.7,-1*(distance-0.5))));
	}
		break;
	case 16:
	{
		double relative_speed=((inputs[4]-inputs[5])/25.0+1)/2;
		relative_speed=((relative_speed>1)?1.0:relative_speed);
		relative_speed=((relative_speed<0)?0.0:relative_speed);
		outputs[index_active_unit]=-relative_speed;
	}
		break;
	case 17:
	{
		double relative_speed=((inputs[4]-inputs[5])/25.0+1)/2;
		relative_speed=((relative_speed>1)?1.0:relative_speed);
		relative_speed=((relative_speed<0)?0.0:relative_speed);
		outputs[index_active_unit]=relative_speed;
	}
		break;
	case 18:
	{
		double relative_speed=((inputs[4]-inputs[5])/25.0+1)/2;
		relative_speed=((relative_speed>1)?1.0:relative_speed);
		relative_speed=((relative_speed<0)?0.0:relative_speed);
		outputs[index_active_unit]=-std::pow(2.7,relative_speed)/2;
	}
		break;
	case 19:
	{
		double relative_speed=((inputs[4]-inputs[5])/25.0+1)/2;
		relative_speed=((relative_speed>1)?1.0:relative_speed);
		relative_speed=((relative_speed<0)?0.0:relative_speed);
		outputs[index_active_unit]=std::pow(2.7,relative_speed)/2;
	}
		break;
	case 20:
	{
		double relative_speed=((inputs[4]-inputs[5])/25.0+1)/2;
		relative_speed=((relative_speed>1)?1.0:relative_speed);
		relative_speed=((relative_speed<0)?0.0:relative_speed);
		outputs[index_active_unit]=-std::log(relative_speed+1);
	}
		break;
	case 21:
	{
		double relative_speed=((inputs[4]-inputs[5])/25.0+1)/2;
		relative_speed=((relative_speed>1)?1.0:relative_speed);
		relative_speed=((relative_speed<0)?0.0:relative_speed);
		outputs[index_active_unit]=std::log(relative_speed+1);
	}
		break;
	case 22:
	{
		double relative_speed=((inputs[4]-inputs[5])/25.0+1)/2;
		relative_speed=((relative_speed>1)?1.0:relative_speed);
		relative_speed=((relative_speed<0)?0.0:relative_speed);
		outputs[index_active_unit]=(-1/(1+std::pow(2.7,-1*(relative_speed-0.5))));
	}
		break;
	case 23:
	{
		double relative_speed=((inputs[4]-inputs[5])/25.0+1)/2;
		relative_speed=((relative_speed>1)?1.0:relative_speed);
		relative_speed=((relative_speed<0)?0.0:relative_speed);
		outputs[index_active_unit]=(1/(1+std::pow(2.7,-1*(relative_speed-0.5))));
	}
		break;
		// enemy is attacking (positive)		
	case 24:
		outputs[index_active_unit]=(2*inputs[6]-1)/2;
		break;
		// enemy is attacking (negative)	
	case 25:
		outputs[index_active_unit]=(1-2*inputs[6])/2;
		break;	
		// in attack distance (postive)
	case 26:
	{
		double distance=std::abs(inputs[2]-inputs[3]);
		if(distance<=245.0)
			outputs[index_active_unit]=0.5;
		else
			outputs[index_active_unit]=-0.5;
	}
		break;
		// in attack distance (negative)
	case 27:
	{
		double distance=std::abs(inputs[2]-inputs[3]);
		if(distance<=245.0)
			outputs[index_active_unit]=-0.5;
		else
			outputs[index_active_unit]=0.5;
	}
		break;	
		// agent is in corner (positive)
	case 28:
		if(inputs[2]>930.0 || inputs[2]<30.0)
			outputs[index_active_unit]=0.5;
		else
			outputs[index_active_unit]=-0.5;
		break;
		// agent is in corner (negative)
	case 29:
		if(inputs[2]>930.0 || inputs[2]<30.0)
			outputs[index_active_unit]=-0.5;
		else
			outputs[index_active_unit]=0.5;
		break;
/*
		// enemy can hit (postive)
	case 36:
		outputs[index_active_unit]=(2*inputs[8]-1);
		//cout<<"case19:"<<outputs[index_active_unit]<<endl;
		break;
		// enemy can hit (negative)
	case 37:
		outputs[index_active_unit]=(1-2*inputs[8]);
		//cout<<"case20:"<<outputs[index_active_unit]<<endl;
		break;	
*/	
	}
}

void interpreter_combination( const int& number,
                               const vector<double>& inputs,
							   double& output)
{
	switch( number )
	{
		// summation
	case 0:
		for(int i=0;i<inputs.size();i++)
			output+=inputs[i];
		break;
		// average
	case 1:
		for(int i=0;i<inputs.size();i++)
			output+=inputs[i];
		output/=inputs.size();
		break;
		// max
	case 2:
		output=INT32_MIN;
		for(int i=0;i<inputs.size();i++)
		{
			if(inputs[i]>output)
				output=inputs[i];
		}	
		break;
		// min
	case 3: 
		output=INT32_MAX;
		for(int i=0;i<inputs.size();i++)
		{
			if(inputs[i]<output)
				output=inputs[i];
		}	
		break;
	}
	
}
	
void interpreter_constant( 	   const int& number,
                               double& output )
{

	switch( number )
	{
/*
		// multiply 0.125
	case 1:
		output*=0.125;
		break;
*/
		// multiply 0.25
	case 0:
		output*=0.25;
		break;
		// multiply 0.5
	case 1:
		output*=0.5;
		break;
		// multiply 1.0
	case 2:
		break;
		// multiply 2.0
	case 3:
		output*=2.0;
		break;
		// multiply 4.0
	case 4:
		output*=4.0;
		break;
	/*
		// multiply 8.0
	case 7:
		output*=8.0;
		break;
	*/

	}
}


vector<double> layer_specification( const vector<double>& inputs,
                                     const vector<int>& weights )
{
	//precondition: we should have at least one 1 in weights.
	int number_active_units = std::count( weights.begin(), weights.begin() + number_units_specification, 1 );
	vector<double> outputs( number_active_units, 0.0 );
	int index_active_unit = 0;
	for( int i = 0; i < number_units_specification; ++i )
	{
		if(weights[i]==1)
		{
			interpreter_specification( i, inputs, index_active_unit, outputs );
			++index_active_unit;
		}
	}
	return outputs;
}

double layer_combination( const vector<double>& inputs,
                          const vector<int>& weights )
{
	double output=0.0;
	for(int i=number_units_specification;i<number_units_specification + number_units_combination;i++)
	{
		if(weights[i]==1)
		{
			interpreter_combination(i-number_units_specification,inputs,output);
			break;
		}
	}
	return output;
}

double layer_constant( double& input,
					   const vector<int>& weights
                          )
{
	double output=input;
	for(int i=number_units_specification + number_units_combination;i<number_units_specification + number_units_combination +number_units_constant;i++)
	{
		if(weights[i]==1)
		{
			interpreter_constant(i-number_units_specification-number_units_combination,output);
			break;
		}
	}
	return output;
}

double intermediate_g( const vector<double>& inputs,
                       const vector<int>& weights)
{
	// heavy penalty for having 0 active transformation weights or not exactly 1 active comparison weight

	if( std::count( weights.begin(), weights.begin() + number_units_specification, 1 ) == 0 )
		return __DBL_MAX__;
	if(std::count( weights.begin() + number_units_specification, weights.begin() + number_units_specification +number_units_combination, 1 )!=1)
		return __DBL_MAX__;
	if(std::count( weights.begin() + number_units_specification+number_units_combination, weights.begin() + number_units_specification +number_units_combination +number_units_constant, 1 )!=1)
		return __DBL_MAX__;

	// transformation layer
	//        |
	//        v
	// combination layer
	//        |
	//        v
	// constant layer
	auto output_spec = layer_specification( inputs,  weights );
	auto output_comb = layer_combination (output_spec,  weights);

	return layer_constant( output_comb ,weights);

}

// Int vector with int config version
double g( const vector<int>& weights,
          const vector<int>& vars,
          int start,
          int nb_vars )

{
	vector<double> inputs( nb_vars );
	std::copy( vars.begin() + start, vars.begin() + start + nb_vars, inputs.begin() );
	return intermediate_g( inputs, weights );
}
