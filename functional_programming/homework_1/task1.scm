#lang racket

(define (to-negative x) 
  (if (< x 0)
      x
      (* -1 x)
      )
  )

(define (to-negative-list l) (map to-negative l))

(define (filter-properly-divisible l) (filter (lambda(x) (= (remainder x 3) 2)) l))

(define (sum-negative-list l) (apply + (to-negative-list (filter-properly-divisible l))))

