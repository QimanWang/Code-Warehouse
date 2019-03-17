
;SOLUTION TO PROBLEM A 

(defun MY-INDEX (N L)
	(let ((X (index (- N 1) (cdr L))))
	
	X))
	
;SOLUTION TO PROBLEM B

(defun MY-MIN-FIRST (L)
	(let((X (min-first (cdr L))))
	
	(if(> (car L) (car X))
	
	(cons (car X) (cons (car L) (cdr X)))
	
	L)))
	
;SOLUTION TO PROBLEM C

(defun MY-SSORT (L)
	(let* ((L1 (min-first L))
			(X (ssort (cdr L1))))
			
	(cons (car L1) X)))
	
;SOLUTION TO PROBLEM 1

(defun INDEX (N L)
	(if(endp L)
		'ERROR
		(if(= N 1)
			(car L)
	(index (- N 1) (cdr L)))))
	
;SOLUTION TO PROBLEM 2

(defun MIN-FIRST (L)
	(if (= (length L) 1)
		L
		
		(let ((X (min-first (cdr L))))
		
			(if (> (car L) (car X))
				(cons (car X) (cons (car L) (cdr X)))
			
			L))))
			
;SOLUTION TO PROBLEM 3

(defun ssort (L)

	(if (endp L)
	
		'NIL
		
		(let* ((L1 (min-first L))
				(X (ssort (cdr L1))))
			
	(cons (car L1) X))))
	
	
;SOLUTION TO PROBLEM 4

(defun QSORT(L)
	(cond ((endp L) ())
			(T (let((PL (partition L (car L))))
					(cond ((endp (car PL)) (ssort (cadr PL)))
							(T (append (qsort (car PL)) (qsort (cadr PL)))))))))
							
							
;SOLUTION TO PROBLEM 5

(defun MERGE-LISTS (L1 L2)

	(if (endp L1)
		L2
		
		(if (endp L2)
			L1
			
			(let ((X (merge-lists (cdr L1) L2))
			
				(Y (merge-lists L1 (cdr L2))))
				
				(if (< (car L1) (car L2))
				
				(cons (car L1) X)
				
				(cons (car L2) Y))))))
				
				
;SOLUTION TO PROBLEM 6

(defun MSORT (L)

	(if (endp L)
	
		'NIL
		
		(if (= (length L) 1)
		
			L
			
			(let* ((X (split-list L)))
			
				(merge-lists (msort (car X)) (msort (cadr X)))))))
				
;SOLUTION TO PROBLEM 7

(defun REMOVE-ADJ-DUPL (L)

(if (endp L)
	()
	
	(if (= (length L) 1)
		L
		
		(let ((X (remove-adj-dupl (cdr L))))
		
			(if (equal (car L) (car X))
			
			X
			
			(cons (car L) X))))))
			
; SOLUTION TO PROBLEM 8

(defun UNREPEATED-ELTS (L)

	(cond ((endp L) ())
			
			((or (endp (cdr L)) (not (equal (car L) (cadr L)))) (cons (car L) (unrepeated-elts (cdr L))))
			
			((or (endp (cddr L)) (not (equal (car L) (caddr L)))) (unrepeated-elts (cddr L)))
			
			(T (unrepeated-elts (cdr L)))))
		
		
;SOLUTION TO PROBLEM 9

(defun REPEATED-ELTS (L)

	(cond ((endp L) ())
	
		((or (endp (cdr L)) (not (equal (car L) (cadr L)))) (repeated-elts (cdr L)))
		
		((or (endp (cddr L)) (not (equal (car L) (caddr L)))) (cons (car L) (repeated-elts (cddr L))))
		
		(T (repeated-elts (cdr L)))))
		
		
;SOLUTION TO PROBLEM 10

(defun COUNT-REPETITIONS (L)
	(if (endp L)
		()
		
		(let ((X (count-repetitions (cdr L))))
		
			(if (or (endp (cdr L)) (not (equal (car L) (cadr L))))
			
				(cons (list 1 (car L)) X)
				
				(cons (list (+ (caar X) 1) (car L)) (cdr X))))))
				
				
;SOLUTION OT PROBLEM 11

(defun SUBSET (F L)

	(cond ((endp L) ())
	
		((funcall F(car L)) (cons (car L) (subset F (cdr L))))
		
		(T (subset F (cdr L)))))
		
		
;SOLUTION TO PROBLEM 12

(defun OUR-SOME (F L)

	(if (endp L)
		nil
		
		(if (funcall F (car L))
			L
			
			(our-some F (cdr L)))))
			
			
(defun OUR-EVERY (F L)

	(if (endp L)
		T
		(and (funcall F(car L)) (our-every F (cdr L)))))
		
		
;SOLUTION TO PROBLEM 13

(defun PARTITION2 (F L P)

	(if (endp L)
	
		'(() ())
		
		(let ((X (partition2 F (cdr L) P)))
		
		(if (funcall F (car L) P)
			
			(list (cons (car L) (car X)) (cadr X))
			
			(list (car X) (cons (car L) (cadr X)))))))
			
			
(defun QSORT1 (F L)

	(cond ((endp L) ())
	
		(else
		
		(let ((X (partition2 F L (car L))))
		
		(cond ((endp (car X)) (cons (car L) (qsort1 F (cdr L))))
		
		(T (append (qsort1 F (car X)) (qsort1 F (cadr X)))))))))
		
		

			
;SOLUTION TO PROBLEM 14	

(defun FOO (F L)
	(if (endp L)
		nil
		
		(let* ((x (foo F (cdr L)))
				(y (mapcar (lambda (x) (cons (car L) x)) (cons (cdr L) x))))
				
				(cons (cons (funcall f (caar y)) (cdar y)) (cdr y)))))		
			
			
;SOLUTION TO PROBLEM 15 (A)

(defun TR-ADD (L RES)

	(if (endp L)
		
		RES
		
		(tr-add (cdr L) (+ (car L) RES))))
		
		
(defun TR-MUL (L RES)

	(if (endp L)
		
		RES
		
		(tr-mul (cdr L) (* (car L) RES))))
		
		
		
(defun TR-FAC (N RES)

	(if (zerop N)
		
		RES
		
		(tr-fac (- N 1) (*  N RES))))
		
		
;SOLUTION TO PROBLEM 15 (B)

(defun SLOW-PRIMEP (N)

	(= (funcall #'mod (tr-fac (- N 1) 1) N) (- N 1)))
	
	
	
;SOLUTION TO PROBLEM 16 (A)

(defun TRANSPOSE1 (M)

	(if (endp (cdr M))
	
		(mapcar #'list (car M))
		
		(mapcar #'cons (car M) (transpose1 (cdr M)))))
		
		
;SOLUTION TO PROBLEM 16 (B)

(defun TRANSPOSE2 (M)

	(if (endp (cdar M))
	
		(list (mapcar #'car M))
		
		(cons (mapcar #'car M) (transpose2 (mapcar #'cdr M)))))
		
		
		
;SOLUTION TO PROBLEM 16 (C)	

(defun TRANSPOSE3 (M)

	(apply #'mapcar #'list M))

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	