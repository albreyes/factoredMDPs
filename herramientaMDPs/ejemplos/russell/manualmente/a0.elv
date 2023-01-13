// Bayesian Network
//   Elvira format 

bnet  "Untitled2" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = (presente , ausente);

// Variables 

node x(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =242;
pos_y =141;
relevance = 7.0;
purpose = "";
num-states = 4;
states = ("S0" "S1" "S2" "S3");
}

node x_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =592;
pos_y =145;
relevance = 7.0;
purpose = "";
num-states = 4;
states = ("S0" "S1" "S2" "S3");
}

node y(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =246;
pos_y =226;
relevance = 7.0;
purpose = "";
num-states = 3;
states = ("S0" "S1" "S2");
}

node y_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =591;
pos_y =236;
relevance = 7.0;
purpose = "";
num-states = 3;
states = ("S0" "S1" "S2");
}

// Links of the associated graph:

link x x_prime;

link y x_prime;

link y y_prime;

//Network Relationships: 

relation x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.273 0.18 0.273 0.274 );
}

relation y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.364 0.272 0.364 );
}

relation y_prime y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.99998 1.0E-5 1.0E-5 1.0E-5 0.99998 1.0E-5 1.0E-5 1.0E-5 0.99998 );
}

relation x_prime x y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.067 0.8 0.067 0.067 1.0E-5 0.067 0.067 0.067 0.067 0.067 0.067 0.067 0.8 0.067 0.8 0.067 0.99997 0.067 0.067 0.067 0.067 0.067 0.067 0.067 0.067 0.067 0.067 0.8 1.0E-5 0.8 0.067 0.067 0.067 0.067 0.067 0.067 0.066 0.066 0.066 0.066 1.0E-5 0.066 0.799 0.799 0.799 0.799 0.799 0.799 );
}

}
