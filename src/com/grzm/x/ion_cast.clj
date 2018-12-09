(ns com.grzm.x.ion-cast
  (:require [datomic.ion.cast :as cast]))

(comment

  ;; redirect to STDOUT
  (cast/initialize-redirect :stdout)

  ;; cast an event
  (cast/event "Foo")

  :end)
