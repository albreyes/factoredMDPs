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
num-states = 20;
states = (S0 S1 S2 S3 S4 S5 S6 S7 S8 S9 S10 S11 S12 S13 S14 S15 S16 S17 S18 S19);
}

node y(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 20;
states = (S0 S1 S2 S3 S4 S5 S6 S7 S8 S9 S10 S11 S12 S13 S14 S15 S16 S17 S18 S19);
}

node x_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 20;
states = (S0 S1 S2 S3 S4 S5 S6 S7 S8 S9 S10 S11 S12 S13 S14 S15 S16 S17 S18 S19);
}

node y_prime(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 20;
states = (S0 S1 S2 S3 S4 S5 S6 S7 S8 S9 S10 S11 S12 S13 S14 S15 S16 S17 S18 S19);
}

// Links of the associated graph:

link x x_prime;

link y y_prime;

//Network Relationships: 

relation x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.053714859437751006 0.045180722891566265 0.05572289156626506 0.06124497991967871 0.05070281124497992 0.0356425702811245 0.04568273092369478 0.050200803212851405 0.05421686746987952 0.048694779116465865 0.04919678714859438 0.04618473895582329 0.046686746987951805 0.043674698795180725 0.05923694779116466 0.051706827309236945 0.05572289156626506 0.053714859437751006 0.05321285140562249 0.03965863453815261 );
}

relation y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.06475903614457831 0.06275100401606426 0.05622489959839357 0.05220883534136546 0.05622489959839357 0.05070281124497992 0.050200803212851405 0.055220883534136546 0.05923694779116466 0.05120481927710843 0.058734939759036146 0.05070281124497992 0.04718875502008032 0.046686746987951805 0.05421686746987952 0.045180722891566265 0.04568273092369478 0.045180722891566265 0.04718875502008032 5.02008032128514E-4 );
}

relation x_prime x { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 );
}

relation y_prime y { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.015413875968992246 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9844061240310077 0.01594333333333333 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9759433333333333 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.007943333333333332 0.99981 0.02878948717948718 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9614817948717949 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.00955871794871795 0.9909814285714286 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.008838571428571428 0.99981 0.009909999999999999 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.98991 0.01809181818181818 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9817281818181818 0.016892485875706215 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9660450282485876 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.016892485875706215 0.99981 0.008457008547008546 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9913629914529914 0.019711980198019802 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9801080198019801 0.02118659574468085 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9786334042553192 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 0.009169259259259259 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9906507407407407 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.99981 0.02213222222222222 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9776877777777778 0.02118659574468085 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 1.0E-5 0.9786334042553192 0.99981 );
}

}