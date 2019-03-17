;solution to problem 1

(defun MY-SUM (L)
	
	(if(endp L)
		0
		(let((x(MY-SUM(cdr L))))
		(+ (car L) x))))
		
;solution to problem 2

(defun MY-NEG-NUMS (L)

	(if(endp L)
		'()
		(let((x(MY-NEG-NUMS(cdr L))))
		
			(if(minusp (car L))
				(cons (car L) x) x))))
				

;solution to problem 3

(defun MY-INC-LIST-2 (L N)

	(if(endp L)
		'()
	(let((x(INC-LIST-2(cdr L) N)))
	(cons (+ (car L) N) x))))
	
	

;solution to problem 4

(defun MY-INSERT (N L)
	
	(if(endp L)
		
		(cons N L)
		(let((x(MY-INSERT N (cdr L))))
		
		(if(< (car L) N)
		(cons (car L) x)
			(cons N L)))))
			
			
;solution to problem 5

(defun MY-ISORT (L)

	(if(endp L)
	'Nil
	(let((x(MY-ISORT (cdr L))))
	(MY-INSERT (car L) X))))
	
	
;solution to problem 6

(defun MY-SPLIT-LIST (L)

	(if(endp L)
	'(() ())
	(let((x(MY-SPLIT-LIST(cdr L))))
	
	(append(list(cons(car L) (second x))) (list(car x))))))
	
	
;solution to problem 7

(defun MY-PARTITION (L P)

	(if(endp L)
	'(() ())
	(let ((x(MY-PARTITION (cdr L) P)))
	
	(if(< (car L) P) (list (cons(car L) (car x)) (cadr x))
	(list (car x) (cons (car L) (cadr x)))))))
	
	
;solution to problem 8

(defun POS (E L)

	(cond ((endp L) 0)
	((equal E (car L)) 1)
	(T (let ((x (POS E (cdr L))))
	
	(if(zerop x) 0
	(+ 1 x))))))
	
	
;solution to problem 9

(defun SPLIT-NUMS (N)
	
	(if(zerop N)
	'((0) ())
	(let((x (SPLIT-NUMS(- N 1))))
	
	(if(evenp N) 
	(list(cons N (car x)) (cadr x))
	(list(car x) (cons N (cadr x)))))))
	

;solution to problem 10

(defun SET-UNION (S1 S2)

	(if(endp S1)
	S2
	(let ((x (SET-UNION (cdr S1) S2)))
	
	(if (member (car S1) x)
	x
	(cons (car S1) x)))))	
	
	
;solution to problem 11

(defun SET-REMOVE (x s)

	(if(endp s)
	'()
	(let ((y (SET-REMOVE x (cdr s))))
	
	(if(or (member (car s) y) (eq (car s) x))
	y
	(cons (car s) y)))))
	
	
;solution to problem 12

(defun SET-EXCL-UNION (S1 S2)

	(if(endp S1)
	S2
	(let ((x (SET-EXCL-UNION (cdr S1) S2)))
	
	(if(member (car S1) x)
	(SET-REMOVE (car S1) x)
	(cons (car S1) x)))))
	
	
;solution to problem 13

(defun SINGLETONS (e)

	(if (endp e)
	'()
	(let ((x (SINGLETONS (cdr e))))
	
	(if (member (car e) x)
	(SET-REMOVE (car e) x)
	(SET-REMOVE (car e) e)))))
	
	