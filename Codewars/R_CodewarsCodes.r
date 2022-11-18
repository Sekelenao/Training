#Rock Paper Scissors. Let's play! You have to return which player won! In case of a draw return Draw!.

rps <- function(p1, p2){
  if(p1==p2){return("Draw!")}
  rock <- c('scissors' = "Player 1 won!", 'paper' = "Player 2 won!")
  paper <- c('rock' = "Player 1 won!", 'scissors' = "Player 2 won!")
  scissors <- c('rock' = "Player 2 won!", 'paper' = "Player 1 won!")
  all <- list('rock' = rock, 'paper' = paper, 'scissors' = scissors)
  return(all[[p1]][[p2]])
}

#Convert boolean to string

 boolean_to_string <- function(b) {
   ifelse(b,"TRUE","FALSE")
 }