// Bayesian Network
//   Elvira format 

bnet  "" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node x(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (S0 S1 S2 S3 S4);
}

node y(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (S0 S1 S2 S3 S4);
}

node x_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (S0 S1 S2 S3 S4);
}

node y_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (S0 S1 S2 S3 S4);
}

// Links of the associated graph:

link x x_prime;

link y y_prime;

//Network Relationships: 

relation x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.2524997368698032 0.25060519945268916 0.2498684349015893 0.24576360383117565 0.0012630249447426586 );
}

relation y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.18576991895589937 0.1957688664351121 0.20029470582043996 0.2155562572360804 0.20261025155246817 );
}

relation x_prime x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.002077535084062804 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.997075450882312 0.0016732997340053195 1.0E-5 1.0E-5 1.0E-5 8.270140336251215E-4 0.9970533921321572 0.0029419432743611348 1.0E-5 1.0E-5 1.0E-5 0.001253308133837323 0.9953598034260039 0.0038393897216274096 1.0E-5 1.0E-5 1.0E-5 0.0016782532996349338 0.9961306102783726 0.99996 );
}

relation y_prime y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 );
}

}
