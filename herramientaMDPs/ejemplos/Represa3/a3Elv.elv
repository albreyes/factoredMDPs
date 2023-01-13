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
values= table (0.2113613101330604 0.18833162743091095 0.18935516888433981 0.2113613101330604 0.19959058341862845 );
}

relation y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.22466734902763563 0.23336745138178097 0.2082906857727738 0.19907881269191402 0.1345957011258956 );
}

relation x_prime x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 );
}

relation y_prime y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.99996 0.2916516666666667 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.7083183333333334 0.25551325552825555 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.7444567444717445 0.3213217609254499 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.6786482390745501 0.2889583840304183 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.7110116159695818 );
}

}
