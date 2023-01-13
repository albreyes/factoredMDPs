// Bayesian Network
//   Elvira format 

bnet  "Untitled3" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = (presente , ausente);

// Variables 

node y(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =244;
pos_y =267;
relevance = 7.0;
purpose = "";
num-states = 3;
states = ("S0" "S1" "S2");
}

node x(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =263;
pos_y =84;
relevance = 7.0;
purpose = "";
num-states = 4;
states = ("S0" "S1" "S2" "S3");
}

node x_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =583;
pos_y =90;
relevance = 7.0;
purpose = "";
num-states = 4;
states = ("S0" "S1" "S2" "S3");
}

node y_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =594;
pos_y =233;
relevance = 7.0;
purpose = "";
num-states = 3;
states = ("S0" "S1" "S2");
}

// Links of the associated graph:

link x x_prime;

link x y_prime;

link y y_prime;

//Network Relationships: 

relation y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.364 0.272 0.364 );
}

relation x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.273 0.18 0.273 0.274 );
}

relation x_prime x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.99997 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99997 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99997 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99997 );
}

relation y_prime x y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.1 0.1 0.1 0.8 1.0E-5 0.1 0.1 0.1 0.1 0.1 0.1 0.1 0.8 0.1 0.1 0.1 0.99998 0.1 0.8 0.1 0.1 0.8 0.1 0.1 0.1 0.8 0.8 0.1 1.0E-5 0.8 0.1 0.8 0.8 0.1 0.8 0.8 );
}

}
