;kong solution to 3-2
(defun kSAFE-AVG(x y)
	(if (and (numberp x) (numberp y))
		(/ (+ x y) 2)
		()))
;kong solution to 3-3
(defun KODD-GT-MILLION (x)
	(and (integerp x) (> x 1000000) (oddp x)))

;4A
(defun my-sum(L)
	(let ((x sum (cdr L ))))
		(+ (car L) X))

;4B
(defun my-neg-nums (L)
