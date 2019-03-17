(defun MY-SUM(L)
  (let ((X (sum (cdr L))))
        (+ (car L) X))
