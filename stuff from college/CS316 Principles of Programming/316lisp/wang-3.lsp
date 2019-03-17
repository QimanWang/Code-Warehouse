;Solution to problem 1
(defun MIN-2(A B)
	(cond ( (not (numberp A)) 'ERROR)
		    ( (not (numberp B)) 'ERROR)
		    ( (<= A B) A)
		    ( (<= B A) B)))

;Solution to problem 2
(Defun SAFE-AVG(A B)
  (cond ((not (numberp A)) 'NIL)
		    ((not (numberp B)) 'NIL)
		    (T  (/ (+ A B) 2))))

;Solution to problem 3
(defun ODD-GT-MILLION(A)
	(and (integerp a) (> a 1000000) (oddp a)))

;solution to problem 4
(defun MULTIPLE-MEMBER(x lst)
  (member x (cdr (member x lst))))

;solution to problem 5
(defun MONTH->INTEGER(m)
  (cond ((equalp 'JANAUARY m) 1)
        ((equalp 'FEBRUARY m) 2)
        ((equalp 'MARCH m) 3)
        ((equalp 'APRIL m) 4)
        ((equalp 'MAY m) 5)
        ((equalp 'JUNE m) 6)
        ((equalp 'JULY m) 7)
        ((equalp 'AUGUST m) 8)
        ((equalp 'SEPTEMBER m) 9)
        ((equalp 'OCTOBER m) 10)
        ((equalp 'NOVEMBER m) 11)
        ((equalp 'DECEMBER m) 12)
				(t 'ERROR)))

;solution to problem 6
(defun SCORE->GRADE(s)
  (cond  ((not (numberP s)) ())
         ((>= s 90) 'A)
         ((>= s 87) 'A-)
         ((>= s 83) 'B+)
         ((>= s 80) 'B)
         ((>= s 77) 'B-)
         ((>= s 73) 'C+)
         ((>= s 70) 'C)
         ((>= s 60) 'D)
         (T 'F)))

;solution to provlem 7
(defun GT(x y)
  (and(numberp x) (numberp y) (> x y)))

;solution to problem 8
(defun SAME-SIGN(x y)
  (and (numberp x)
       (numberp y)
       (or (and (zerop x) (zerop y))
           (and (< x 0) (< y 0))
           (and (> x 0) (> y 0)))))

;solution to problem 9
(defun SAFE-DIV(x y)
  (and (numberp x)
       (and (numberp y)(not(zerop y)))
       (/ x y)))
