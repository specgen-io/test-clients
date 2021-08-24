//go:generate specgen service-go --spec-file ./../spec.yaml --module-name test-service --generate-path . --swagger-path docs/swagger.yaml

package main

import (
	"flag"
	"fmt"
	"github.com/husobee/vestigo"
	"log"
	"net/http"
	"test-service/services"
	"test-service/services/v2"
	"test-service/spec"
)

func main() {
	port := flag.String("port", "8081", "port number")
	flag.Parse()

	router := vestigo.NewRouter()

	router.Get("/", func(w http.ResponseWriter, r *http.Request) { w.WriteHeader(200) })

	router.SetGlobalCors(&vestigo.CorsAccessControl{
		AllowOrigin: []string{"*", "*"},
	})

	echoServiceV2 := &v2.EchoService{}
	echoService := &services.EchoService{}
	checkService := &services.CheckService{}

	spec.AddRoutes(router, echoServiceV2, echoService, checkService)

	router.Get("/docs/*", http.StripPrefix("/docs/", http.FileServer(http.Dir("docs"))).ServeHTTP)

	fmt.Println("Starting service on port: " + *port)
	log.Fatal(http.ListenAndServe(":"+*port, router))
}
