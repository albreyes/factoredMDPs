MDPtoolbox_path = pwd;
addpath(MDPtoolbox_path)

clear
clc

A0 = csvread('modeloAccion0.csv', 0, 0);
A1 = csvread('modeloAccion1.csv', 0, 0);
A2 = csvread('modeloAccion2.csv', 0, 0);
A3 = csvread('modeloAccion3.csv', 0, 0);
A4 = csvread('modeloAccion4.csv', 0, 0);
R= csvread('recompensaSXA.csv',0,0);

P(:,:,1)=A0;
P(:,:,2)=A1;
P(:,:,3)=A2;
P(:,:,4)=A3;
P(:,:,5)=A4;

mdp_check(P, R)
 
discount = 0.9;

[V, policy] = mdp_policy_iteration(P, R, discount)

