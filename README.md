# stack-project-4sg

Instructions for running:
Run in order : Turn on mongoDb from command line using mongod;
sg-config-server > sg-eureka-server > sg-zuul > sg-stack-login > sg-stack-excel > sg-stack-company > sg-stack-frontend

Login microservice can be substituted for dockerfile available using : docker pull somakm/sg-stack-login:latest

Rest endpoints accessible through localhost:8088
Angular frontend accessible from localhost:4200

Admin pages created, Pages for user to view charts not present, backend connections to get the data available in CompanyController of sg-stack-company.
