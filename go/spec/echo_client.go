package spec

import "fmt"
import "errors"
import "io/ioutil"
import "net/http"
import "encoding/json"
import "bytes"
import log "github.com/sirupsen/logrus"

type echoClient struct {
	baseUrl string
}

func NewEchoClient(baseUrl string) *echoClient {
	return &echoClient{baseUrl}
}

func (client *echoClient) EchoBody(body *Message) (*EchoBodyResponse, error) {
	bodyJSON, err := json.Marshal(body)
	req, err := http.NewRequest("POST", client.baseUrl+"/echo/body", bytes.NewBuffer(bodyJSON))
	operationId := "http.echo.echo_body"
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

func (client *echoClient) EchoQuery(intQuery int, stringQuery string) (*EchoQueryResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/echo/query", nil)
	operationId := "http.echo.echo_query"
	url := "/echo/query"
	log.WithFields(log.Fields{
		"operationId": operationId,
		"url":         url,
	}).Info("Get request execution")

	if err != nil {
		log.Error(err)
		return nil, err
	}

	query := req.URL.Query()
	q := NewParamsConverter(query)
	q.Int("int_query", intQuery)
	q.String("string_query", stringQuery)
	req.URL.RawQuery = query.Encode()

	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.Error(err)
		return nil, err
	}

	if resp.StatusCode == 200 {
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()

		var body *Message
		err = json.Unmarshal(responseBody, &body)
		log.WithFields(log.Fields{
			"json": *body,
		}).Info("Status code: 200")

		if err != nil {
			log.Error(err)
			return nil, err
		}

		log.Info("Request completed")
		return &EchoQueryResponse{Ok: body}, nil
	}

	log.Info("Request completed")
	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}

func (client *echoClient) EchoHeader(intHeader int, stringHeader string) (*EchoHeaderResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/echo/header", nil)
	operationId := "http.echo.echo_header"
	url := "/echo/header"
	log.WithFields(log.Fields{
		"operationId": operationId,
		"url":         url,
	}).Info("Get request execution")

	if err != nil {
		log.Error(err)
		return nil, err
	}

	header := req.Header
	h := NewParamsConverter(header)
	h.Int("Int-Header", intHeader)
	h.String("String-Header", stringHeader)

	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.Error(err)
		return nil, err
	}

	if resp.StatusCode == 200 {
		responseBody, err := ioutil.ReadAll(resp.Body)
		err = resp.Body.Close()

		var body *Message
		err = json.Unmarshal(responseBody, &body)
		log.WithFields(log.Fields{
			"json": *body,
		}).Info("Status code: 200")

		if err != nil {
			log.Error(err)
			return nil, err
		}

		log.Info("Request completed")
		return &EchoHeaderResponse{Ok: body}, nil
	}

	log.Info("Request completed")
	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}

func (client *echoClient) EchoUrlParams(intUrl int, stringUrl string) (*EchoUrlParamsResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+fmt.Sprintf("/echo/url_params/%s/%s", convertInt(intUrl), stringUrl), nil)
	operationId := "http.echo.echo_url_params"
	url := "/echo/url_params/{int_url:int}/{string_url:string}"
	log.WithFields(log.Fields{
		"operationId": operationId,
		"url":         url,
	}).Info("Get request execution")

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

		var body *Message
		err = json.Unmarshal(responseBody, &body)
		log.WithFields(log.Fields{
			"json": *body,
		}).Info("Status code: 200")

		if err != nil {
			log.Error(err)
			return nil, err
		}

		log.Info("Request completed")
		return &EchoUrlParamsResponse{Ok: body}, nil
	}

	log.Info("Request completed")
	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}
