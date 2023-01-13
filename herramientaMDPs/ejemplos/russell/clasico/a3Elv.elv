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
num-states = 4;
states = (S0 S1 S2 S3);
}

node y(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (S0 S1 S2);
}

node x_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (S0 S1 S2 S3);
}

node y_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (S0 S1 S2);
}

// Links of the associated graph:

link x x_prime;

link y y_prime;

//Network Relationships: 

relation x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.20606060606060606 0.20606060606060606 0.30303030303030304 0.28484848484848485 );
}

relation y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (1.0E-5 0.509085909090909 0.49090409090909093 );
}

relation x_prime x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.99997 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99997 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99997 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99997 );
}

relation y_prime y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (1.0E-5 0.99998 1.0E-5 1.0E-5 1.0E-5 0.99998 1.0E-5 1.0E-5 1.0E-5 );
}

}
