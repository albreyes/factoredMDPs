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
values= table (0.19870194707938094 0.19570644033949075 0.22116824762855716 0.2276585122316525 0.1567648527209186 );
}

relation y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.27908137793310034 0.21218172740888666 0.19970044932601097 0.18172740888667 0.127309036445332 );
}

relation x_prime x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.7663166582914573 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.23365334170854274 0.7550870408163265 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.24488295918367345 0.7245899661399549 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.27538003386004517 0.7806867543859649 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.2192832456140351 0.99996 );
}

relation y_prime y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99996 );
}

}
