import math
import numpy
from pomegranate import *

# The winter season is one of the four season 
winter=DiscreteDistribution({'True':0.25,'False':0.75})

# The weather represents the optimal condition fo
good_weather = DiscreteDistribution({'True':0.15,'False':0.85})

# Hunting is dependent on both the winter and the good_weather.
hunt = ConditionalProbabilityTable(
		#['winter','good_wether','hunt',p(hunt)]
                [['True','True','True',0.84],
 		 ['True','False','True',0.76],
 		 ['False','True','True',0.35],
 		 ['False','False','True',0.15],

 		 ['True','True','False',0.16],
 		 ['True','False','False',0.24],
 		 ['False','True','False',0.65],
 		 ['False','False','False',0.85]],[winter,good_weather])

w = State( winter, name="winter" )
g = State( good_weather, name="good_weather" )
h = State( hunt, name="hunt" )

model= BayesianNetwork( "Hunt Problem" )
model.add_states(w, g, h)
model.add_edge(w, h)
model.add_edge(g, h)
model.bake()

print(model.probability(numpy.array(['True','False','True'], ndmin=2)))
