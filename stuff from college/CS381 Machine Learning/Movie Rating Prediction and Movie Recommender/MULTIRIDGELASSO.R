calc_Rsquared = function(mse,ess){
  1 - (mse/ess)
}

require(DAAG)
require(car)
require(moments)
require(glmnet)


movies = read.csv("movie_metadata.csv")
movieUSA = subset(movies, country == "USA") # only using USA
movieUSA = na.omit(movies) #Take out empty data
movieUSA$Colord[movieUSA$color == "Color"] = 1 #Color dummy variable
movieUSA$Colord[movieUSA$color != "Color"] = 0


#Multiple Regression

mUSArelevantvars = movieUSA[, c("imdb_score", "director_facebook_likes", "duration", "actor_1_facebook_likes", "actor_2_facebook_likes",
                                "actor_3_facebook_likes", "title_year",  "facenumber_in_poster", "Colord","budget")]
                                    
skewness(mUSArelevantvars$imdb_score) #skewed, evidence for not normal
hist(mUSArelevantvars$imdb_score)
noregressors = lm(imdb_score ~ 1, data = mUSArelevantvars) #The model with an intercept ONLY.
allregressors = lm(imdb_score ~ ., data = mUSArelevantvars) #The model with ALL variables

RangeofModels = list(lower = formula(noregressors), upper = formula(allregressors))

StepModel = step(noregressors, RangeofModels, direction = "forward", k = 2)

summary(StepModel) #step regression take out actor 3

plot(StepModel) # normality not satisfied

NormalizedModel = boxCox(StepModel) #makes model more linear, homosckedastic, normal errors
NormalizedModel$y
lambda = NormalizedModel$x[which(NormalizedModel$y == max(NormalizedModel$y))] # Extracting the best lambda value
IMDB.Norm = (movieUSA$imdb_score^lambda - 1)/lambda #transforms output vars
skewness(IMDB.Norm) # alot less skewess
hist(IMDB.Norm)
cv.lm(data = mUSArelevantvars,allregressors, m=10) # 10 fold cross-validation

BoxCoxModel = lm(IMDB.Norm ~ director_facebook_likes + facenumber_in_poster + title_year + Colord + 
                   actor_1_facebook_likes + actor_2_facebook_likes + budget + duration, data=movieUSA)

summary(BoxCoxModel)
 plot (BoxCoxModel) #normality assumption holds
x = as.matrix(mUSArelevantvars[, -1]) #evrything but imdb score
target = mUSArelevantvars[, 1] #imdb score


#Ridge Regression

lambdarange = 10^seq(5, -2, length = 100)
ridge.models = glmnet(x, target, alpha = 0, lambda = lambdarange)

# Visualizing the ridge regression shrinkage.
plot(ridge.models, xvar = "lambda", label = TRUE, main = "Ridge Regression")
set.seed(0)
training.set = sample(1:nrow(x), 7*nrow(x)/10)
testing.set = (-training.set)
y.test = target[testing.set]

length(training.set)/nrow(x)
length(y.test)/nrow(x)
set.seed(0) #Running 10-fold cross validation.
CVRR = cv.glmnet(x[training.set, ], target[training.set], lambda = lambdarange,  type.measure='mse',alpha = 0, nfolds = 10)
names(CVRR)
plot(CVRR, main = "Ridge Regression\n")
mse=CVRR$cvm[CVRR$lambda == CVRR$lambda.min]

optimallambdaRR = CVRR$lambda.min
optimallambdaRR #best lambda for Ridge Regression
ridge.bestlambdatrain = predict(ridge.models, s = optimallambdaRR, newx = x[testing.set, ])
Ridgefort = glmnet(x, target, alpha = 0)
#Ridge Model
predict(Ridgefort, type = "coefficients", s = optimallambdaRR)
#Let's also inspect the MSE of our final ridge model on all our data.
ridge.bestlambda = predict(Ridgefort, s = optimallambdaRR, newx = x)

a <-mean((ridge.bestlambda - target)^2) #MSE
b <-mean((y.test - mean(y.test))^2)
Ridge.rsq =  1 - a/b
calc_Rsquared(a,b)

#Lasso Regression
lasso.models = glmnet(x, target, alpha = 1, lambda = lambdarange) #Fit LASSO regression
coef(lasso.models) #All coeffiecient estimates
set.seed(0)
#10 Fold cross validation
CVLR = cv.glmnet(x[training.set, ], target[training.set], lambda = lambdarange, alpha = 1, nfolds = 10) #Run K-Fold CV for lambda
plot(CVLR, main = "LASSO Regression\n")
optimallambda.lasso = CVLR$lambda.min
optimallambda.lasso
log(optimallambda.lasso)
lasso.bestlambdatrain = predict(lasso.models, s = optimallambda.lasso, newx = x[testing.set, ])
mean((lasso.bestlambdatrain - y.test)^2)
lasso.out = glmnet(x, target, alpha = 1)
predict(lasso.out, type = "coefficients", s = optimallambda.lasso)
OptimalLRR = predict(lasso.out, s = optimallambda.lasso, newx = x)
c <- mean((OptimalLRR - target)^2)
c #MSE
calc_Rsquared(c,b) #R^2 LASSO

#BOTH MODELS
predict(Ridgefort, type = "coefficients", s = optimallambdaRR)
predict(lasso.out, type = "coefficients", s = optimallambda.lasso)

mean((ridge.bestlambda - target)^2)
mean((OptimalLRR - target)^2)



