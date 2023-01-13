// Bayesian Network
//   Elvira format 

bnet  "" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node Q(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 44;
states = (q0 q1 q2 q3 q4 q5 q6 q7 q8 q9 q10 q11 q12 q13 q14 q15 q16 q17 q18 q19 q20 q21 q22 q23 q24 q25 q26 q27 q28 q29 q30 q31 q32 q33 q34 q35 q36 q37 q38 q39 q40 q41 q42 q43);
}

node Q_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 44;
states = (q0 q1 q2 q3 q4 q5 q6 q7 q8 q9 q10 q11 q12 q13 q14 q15 q16 q17 q18 q19 q20 q21 q22 q23 q24 q25 q26 q27 q28 q29 q30 q31 q32 q33 q34 q35 q36 q37 q38 q39 q40 q41 q42 q43);
}

// Links of the associated graph:

//Network Relationships: 

relation Q { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.009094529183061098 0.008137133299863396 0.011966716832654205 0.005264945650270289 0.004307549767072587 0.0038288518254737357 0.004307549767072587 0.0023927580006771823 0.015317602423846163 4.77966234281778E-4 0.0033501538838748846 1.0E-5 0.004786247708671438 0.11632286810120375 0.009094529183061098 4.77966234281778E-4 0.4068925186517064 0.01818979007343927 4.77966234281778E-4 0.004307549767072587 0.0014353621174794803 1.0E-5 0.0033501538838748846 0.0014353621174794803 0.0023927580006771823 0.009094529183061098 4.77966234281778E-4 0.0019140600590783314 0.004307549767072587 0.0019140600590783314 0.011488018891055354 1.0E-5 0.03542291597099791 0.05313473981015541 0.04116729127018413 9.56664175880629E-4 9.56664175880629E-4 0.13786427547315205 0.03829510362059102 9.56664175880629E-4 4.77966234281778E-4 0.017232394190241567 0.004786247708671438 0.0019140600590783314 );
}

relation Q_prime { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.009094529183061098 0.008137133299863396 0.011966716832654205 0.005264945650270289 0.004307549767072587 0.0038288518254737357 0.004307549767072587 0.0023927580006771823 0.015317602423846163 4.77966234281778E-4 0.0033501538838748846 1.0E-5 0.004786247708671438 0.11632286810120375 0.009094529183061098 4.77966234281778E-4 0.4068925186517064 0.01818979007343927 4.77966234281778E-4 0.004307549767072587 0.0014353621174794803 1.0E-5 0.0033501538838748846 0.0014353621174794803 0.0023927580006771823 0.009094529183061098 4.77966234281778E-4 0.0019140600590783314 0.004307549767072587 0.0019140600590783314 0.011488018891055354 1.0E-5 0.03542291597099791 0.05313473981015541 0.04116729127018413 9.56664175880629E-4 9.56664175880629E-4 0.13786427547315205 0.03829510362059102 9.56664175880629E-4 4.77966234281778E-4 0.017232394190241567 0.004786247708671438 0.0019140600590783314 );
}

}
