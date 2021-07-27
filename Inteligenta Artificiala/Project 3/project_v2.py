import math
import numpy
from pomegranate import *

# The winter season is one of the four season 
winter=DiscreteDistribution({'True':0.25,'False':0.75})

# The weather represents the optimal condition for hunting
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

bear = ConditionalProbabilityTable(
		#[hunt,bear,p(bear)]
                [['True','True',0.18],
		 ['True','False',0.85],

		 ['False','True',0.05],
		 ['False','False',0.95]],[hunt])

deer = ConditionalProbabilityTable(
		#[hunt,deer,p(deer)]
                [['True','True',0.83],
		 ['True','False',0.45],

		 ['False','True',0.17],
		 ['False','False',0.55]],[hunt])

wild_pig = ConditionalProbabilityTable(
		#[hunt ,wild_pig, p(wild_pig)]
                [['True','True',0.93],
		 ['True','False',0.55],

		 ['False','True',0.07],
		 ['False','False',0.45]],[hunt])

jeep = ConditionalProbabilityTable(
		#['bear','deer','jeep',p(jeep)]
                [['True','True','True',0.94],
 		 ['True','False','True',0.86],
 		 ['False','True','True',0.65],
 		 ['False','False','True',0.15],

 		 ['True','True','False',0.06],
 		 ['True','False','False',0.14],
 		 ['False','True','False',0.35],
 		 ['False','False','False',0.85]],[bear,wild_pig])

backpack = ConditionalProbabilityTable(
		#[deer ,backpack, p(backpack)]
                [['True','True',0.97],
		 ['True','False',0.55],

		 ['False','True',0.03],
		 ['False','False',0.45]],[deer])

w = State( winter, name="winter" )
g = State( good_weather, name="good_weather" )
h = State( hunt, name="hunt" )
b = State( bear, name="bear" )
d = State( deer, name="deer" )
p = State( wild_pig, name="wild_pig" )
j = State( jeep, name="jeep" )
bp = State( backpack, name="backpack" )

model= BayesianNetwork( "Hunt Problem" )

model.add_states(w, g, h, b, d,p,j,bp)
model.add_edge(w, h)
model.add_edge(g, h)

model.add_edge(h, b)
model.add_edge(h, d)
model.add_edge(h, p)

model.add_edge(b, j)
model.add_edge(p, j)

model.add_edge(d, bp)


model.bake()
#['winter','god_weather','hunt','bear','deer','wild_pig','jeep','backpack']	
print("P(J^-BP^B^-D^-P^V^W^G)=",model.probability(numpy.array(['True','True','True','True','False','False','True','False'], ndmin=2)))
print("P(BP^-J^-B^D^-P^V^W^G)=",model.probability(numpy.array(['True','True','True','False','True','False','False','True'], ndmin=2)))
print("P(J^-BP^-B^-D^P^V^W^G)=",model.probability(numpy.array(['True','True','True','False','False','True','True','False'], ndmin=2)))
