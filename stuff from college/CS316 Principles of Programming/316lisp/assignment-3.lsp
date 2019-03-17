;solution to problem 1

(defun MIN-2(A B)
(if(and (numberp A) (numberp B) (<= A B)) A B))


; solution to problem 2

(defun SAFE-AVG(A B)
(and(numberp A) (numberp B) (/ (+ A B) 2)))

;solution to problem 3

(defun ODD-GT-MILLION(input)
(and(integerp input) (oddp input) (> input 1000000)))

;solution to problem 4

(defun MULTIPLE-MEMBER(symbl ls)
(if(and(or(symbolp symbl) (numberp symbl)) (listp ls)) (member symbl(rest(member symbl ls)))) )


;solution to problem 5

(defun MONTH->INTEGER (input)
(cond((eq input 'January) 1)
((eq input 'February) 2)
((eq input 'March) 3)
((eq input 'April) 4)
((eq input 'May) 5)
((eq input 'June) 6)
((eq input 'July) 7)
((eq input 'August) 8)
((eq input 'September) 9)
((eq input 'October) 10)
((eq input 'November) 11)
((eq input 'December) 12)
('ERROR)
))

;solution to problem 6

(defun SCORE->GRADE(s)
(cond((>= s 90) 'A)
((>= s 87) 'A-)
((>= s 83) 'B+)
((>= s 80) 'B)
((>= s 77) 'B-)
((>= s 73) 'C+)
((>= s 70) 'C)
((>= s 60) 'D)
((< s 60) 'F)
('NIL)
))


;solution to problem 7

(defun GT(A B)
(and(numberp A) (numberp B) (> A B) T))

;solution to problem 8

(defun SAME-SIGN (A B)
(or(and (zerop A) (zerop B)) (and (minusp A) (minusp B)) (and (plusp A) (plusp B))))


;solution to problem 9

(defun SAFE-DIV(A B)
(and (numberp A) (numberp B) (not(zerop B)) (/ A B)))

