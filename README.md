# Author
## DennisKingsman
# Correlated project
starts order and send to q to check payment  
[order service](https://github.com/DennisKingsman/ibmMqListenerCorrelationSpringBoot)  
start payment service before order service 
# Issues
if message already in q before service starts then it's trying to process message infinitely
