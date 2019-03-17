
library(ggplot2)
library(VIM)
library(mice)
library(vcd)
require(car)
library(tabplot)
library(PerformanceAnalytics)
library(MASS)
library(glmnet)
library(corrgram)
library(corrplot)
library(ggthemes)
library(dplyr)

movies = read.csv("movie_metadata.csv")

#** basic analysis
#* to run code, remove "#"
#str(movie) #structure of the Data Set
#head(movie) #first 6 data set
#dim(movie) #dimension of dataset (# of data set, Features)
#sapply(movies, sd)

#*correlation diagram
# moviez<- na.omit(movies)
# Num.cols <- sapply(moviez,is.numeric)
# cor.data <- cor(moviez[,Num.cols])
# corrplot(cor.data,method='color')
# qplot(x=year, y=rating, data = moviez, geom = "density2d")

#*shows how many critic does movies get
#hist(movies$num_critic_for_reviews)

#*number of movies produced per year
# temp <- movies %>% select(movie_title,title_year)
# temp <- temp %>% group_by(title_year) %>% summarise(n=n())
# temp <- na.omit(temp)
# p <- plot_ly(temp, x = title_year, y = n, name = "Number of Movies per Year")
# p %>%
#   add_trace(y = fitted(loess(n ~ as.numeric(title_year))), x = title_year) %>%
#   layout(title = "Change in number of movie produced per year",
#          showlegend = FALSE) %>%
#   dplyr::filter(n == max(n)) %>%
#   layout(annotations = list(x = title_year, y = n, text = "Highest Amount", showarrow = T)) 

#*Average rating over the years
# temp <- movies %>% select(imdb_score,title_year)
# temp <- temp %>% group_by(title_year)%>% summarise(score=mean(imdb_score))
# temp <- na.omit(temp)
# p <- plot_ly(temp, x = title_year, y = score, name = "Avg Score by Year")
# p %>%
#   add_trace(y = fitted(loess(score ~ as.numeric(title_year))), x = title_year) %>%
#   layout(title = "Score Over The Years",
#          showlegend = FALSE) %>%
#   dplyr::filter(score == max(score)) %>%
#   layout(annotations = list(x = title_year, y = score, text = "Peak", showarrow = T)) 

#Genre and their rating
#temp <- movie %>% select(content_rating,imdb_score)
#temp <- temp %>% group_by(content_rating)%>% summarise(score = mean(imdb_score))
#plot_ly(
#  x = temp$content_rating,
#  y = temp$score,
#  name = "Avg score by Rating",
#  type = "bar")

