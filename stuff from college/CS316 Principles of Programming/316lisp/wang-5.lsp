;Solution to Problem 1
(defun INDEX (N L)
	(if(endp L) 'ERROR
	  (if(= N 1) (car L)
	    (INDEX (- N 1) (cdr L)))))

;Solution to Problem 2
(defun MIN-FIRST (L)
	(if (= (length L) 1) L
	  (let ((X (MIN-FIRST (cdr L))))
      (if (> (car L) (car X))
				  (cons (car X) (cons (car L) (cdr X)))
          L))))

;Solution to Problem 3
(defun SSORT (L)
	(if (endp L) 'NIL
		(let* ((L1 (MIN-FIRST L))
		  (X (SSORT (cdr L1))))
        (cons (car L1) X))))

;Solution to Problem 4
(defun QSORT(L)
	(cond ((endp L) ())
			  (T (let((PL (PARTITION L (car L))))
             (cond ((endp (car PL)) (SSORT (cadr PL)))
				           (T (append (QSORT (car PL)) (QSORT (cadr PL)))))))))

;Solution to Problem 5
(defun MERGE-LISTS (L1 L2)
	(if (endp L1) L2
	  (if (endp L2) L1
	    (let ((X (MERGE-LISTS (cdr L1) L2))
			(Y (MERGE-LISTS L1 (cdr L2))))
        (if (< (car L1) (car L2))
            (cons (car L1) X)
            (cons (car L2) Y))))))

;Solution to Problem 6
(defun MSORT (L)
	(if (endp L) 'NIL
    (if (= (length L) 1) L
			(let* ((X (SPLIT-LIST L)))
        (MERGE-LISTS(MSORT (car X)) (MSORT (cadr X)))))))

;Solution to Problem 7
(defun REMOVE-ADJ-DUPL (L)
(if (endp L) ()
  (if (= (length L) 1) L
		(let ((X (REMOVE-ADJ-DUPL (cdr L))))
      (if (equal (car L) (car X)) X
			    (cons (car L) X))))))

;Solution to Problem 8
(defun UNREPEATED-ELTS (L)
	(cond ((endp L) ())
        ((or (endp (cdr L)) (not (equal (car L) (cadr L)))) (cons (car L) (UNREPEATED-ELTS (cdr L))))
        ((or (endp (cddr L)) (not (equal (car L) (caddr L)))) (UNREPEATED-ELTS (cddr L)))
        (T (UNREPEATED-ELTS (cdr L)))))

;Solution to Problem 9
(defun REPEATED-ELTS (L)
	(cond ((endp L) ())
        ((or (endp (cdr L)) (not (equal (car L) (cadr L)))) (REPEATED-ELTS (cdr L)))
        ((or (endp (cddr L)) (not (equal (car L) (caddr L)))) (cons (car L) (REPEATED-ELTS (cddr L))))
        (T (REPEATED-ELTS (cdr L)))))

;Solution to Problem 10
(defun COUNT-REPETITIONS (L)
	(if (endp L) ()
    (let ((X (COUNT-REPETITIONS (cdr L))))
      (if (or (endp (cdr L)) (not (equal (car L) (cadr L))))
          (cons (list 1 (car L)) X)
          (cons (list (+ (caar X) 1) (car L)) (cdr X))))))

;Solution to Problem 11
(defun SUBSET (F L)
	(cond ((endp L) ())
        ((funcall F(car L)) (cons (car L) (SUBSET F (cdr L))))
        (T (SUBSET F (cdr L)))))

;Solution to Problem 12
(defun OUR-SOME (F L)
	(if (endp L) 'Nil
	(if (funcall F (car L)) L
			(OUR-SOME F (cdr L)))))

(defun OUR-EVERY (F L)
	(if (endp L) T
		  (and (funcall F(car L)) (OUR-EVERY F (cdr L)))))

;Solution to Problem 13
;No Solution to Problem 13 , There is a bug and I can't figure out how to debug it.
(defun PARTITION1 (F L P)
	(if (endp L) '(() ())
    (let ((X (PARTITION1 F (cdr L) P)))
      (if (funcall F (car L) P)
          (list (cons (car L) (car X)) (cadr X))
          (list (car X) (cons (car L) (cadr X)))))))

(defun QSORT1 (F L)
	(cond ((endp L) ())
    (else
      (let ((X (PARTITION1 F L (car L))))
        (cond ((endp (car X)) (cons (car L) (QSORT1 F (cdr L))))
		          (T (append (QSORT1 F (car X)) (QSORT1 F (cadr X)))))))))


;Solution to Problem 14
(defun FOO (F L)
	(if (endp L) 'Nil
		(let* ((x (FOO F (cdr L)))
		   (y (mapcar (lambda (x) (cons (car L) x)) (cons (cdr L) x))))
          (cons (cons (funcall f (caar y)) (cdar y)) (cdr y)))))

;Solution to Problem 15(A)
(defun TR-ADD (L RES)
  (if (endp L) RES
      (TR-ADD (cdr L) (+ (car L) RES))))

(defun TR-MUL (L RES)
	(if (endp L) RES
      (TR-MUL (cdr L) (* (car L) RES))))

(defun TR-FAC (N RES)
	(if (zerop N) RES
      (TR-FAC (- N 1) (*  N RES))))

;Solution to Problem 15(B)
(defun SLOW-PRIMEP (N)
	(= (funcall #'mod (TR-FAC (- N 1) 1) N) (- N 1)))

;Solution to Problem 16(A)
(defun TRANSPOSE1 (M)
	(if (endp (cdr M))
		(mapcar #'list (car M))
		(mapcar #'cons (car M) (transpose1 (cdr M)))))

;Solution to Problem 16(B)
(defun TRANSPOSE2 (M)
	(if (endp (cdar M))
		(list (mapcar #'car M))
		(cons (mapcar #'car M) (transpose2 (mapcar #'cdr M)))))

;Solution to Problem 16(C)
(defun TRANSPOSE3 (M)
	(apply #'mapcar #'list M))
