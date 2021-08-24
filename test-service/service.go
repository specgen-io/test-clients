//go:generate specgen service-go --module-name test-service --spec-file ./../spec.yaml --generate-path .

package main

import (
	"flag"
	"fmt"
	"github.com/husobee/vestigo"
	"log"
	"net/http"
	"test-service/spec"
	"test-service/v2"
)

func main() {
	port := flag.String("port", "8081", "port number")
	flag.Parse()

	router := vestigo.NewRouter()

	router.Get("/", func (w http.ResponseWriter, r *http.Request) {
		w.WriteHeader(200)
	})

	router.SetGlobalCors(&vestigo.CorsAccessControl{
		AllowOrigin:      []string{"*", "*"},
	})

	spec.AddRoutes(router, &v2.EchoService{}, &EchoService{}, &CheckService{})

	fmt.Println("Starting service on port: "+*port)
	log.Fatal(http.ListenAndServe(":"+*port, router))
}