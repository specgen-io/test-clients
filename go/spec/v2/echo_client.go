package v2

import "bytes"
import "fmt"
import log "github.com/sirupsen/logrus"
import "errors"
import "io/ioutil"
import "net/http"
import "encoding/json"

type echoClient struct {
	baseUrl string
}

func NewEchoClient(baseUrl string) *echoClient {
	return &echoClient{baseUrl}
}

func (client *echoClient) EchoBody(body *Message) (*EchoBodyResponse, error) {
	bodyJSON, err := json.Marshal(body)
	req, err := http.NewRequest("POST", client.baseUrl+"/echo/body", bytes.NewBuffer(bodyJSON))
	operationId := "v2.http.echo.echo_body"
	url := "/echo/body"
	log.WithFields(log.Fields{
		"operationId": operationId,
		"url":         url,
	}).Info("Post request execution")

	if err != nil {
		log.Error(err)
		return nil, err
	}

	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.Error(err)
		return nil, err
	}

	if resp.StatusCode == 200 {
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()

		err = json.Unmarshal(responseBody, &body)
		log.WithFields(log.Fields{
			"json": *body,
		}).Info("Status code: 200")

		if err != nil {
			log.Error(err)
			return nil, err
		}

		log.Info("Request completed")
		return &EchoBodyResponse{Ok: body}, nil
	}

	log.Info("Request completed")
	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}
