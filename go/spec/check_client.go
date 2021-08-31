package spec

import "fmt"
import log "github.com/sirupsen/logrus"
import "errors"
import "io/ioutil"
import "net/http"
import "encoding/json"
import "cloud.google.com/go/civil"
import "github.com/google/uuid"
import "github.com/shopspring/decimal"

type checkClient struct {
	baseUrl string
}

func NewCheckClient(baseUrl string) *checkClient {
	return &checkClient{baseUrl}
}

func (client *checkClient) CheckEmpty() (*CheckEmptyResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/check/empty", nil)
	operationId := "http.check.check_empty"
	url := "/check/empty"
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
		body := &Empty
		log.WithFields(log.Fields{
			"json": *body,
		}).Info("Status code: 200")
		log.Info("Request completed")
		return &CheckEmptyResponse{Ok: body}, nil
	}

	log.Info("Request completed")
	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}

func (client *checkClient) CheckQuery(pString string, pStringOpt *string, pStringArray []string, pDate civil.Date, pDateArray []civil.Date, pDatetime civil.DateTime, pInt int, pLong int64, pDecimal decimal.Decimal, pEnum Choice, pStringDefaulted string) (*CheckQueryResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/check/query", nil)
	operationId := "http.check.check_query"
	url := "/check/query"
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
	q.String("p_string", pString)
	q.StringNullable("p_string_opt", pStringOpt)
	q.StringArray("p_string_array", pStringArray)
	q.Date("p_date", pDate)
	q.DateArray("p_date_array", pDateArray)
	q.DateTime("p_datetime", pDatetime)
	q.Int("p_int", pInt)
	q.Int64("p_long", pLong)
	q.Decimal("p_decimal", pDecimal)
	q.StringEnum("p_enum", pEnum)
	q.String("p_string_defaulted", pStringDefaulted)
	req.URL.RawQuery = query.Encode()

	resp, err := http.DefaultClient.Do(req)
	if err != nil {
		log.Error(err)
		return nil, err
	}

	if resp.StatusCode == 200 {
		body := &Empty
		log.WithFields(log.Fields{
			"json": *body,
		}).Info("Status code: 200")
		log.Info("Request completed")
		return &CheckQueryResponse{Ok: body}, nil
	}

	log.Info("Request completed")
	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}

func (client *checkClient) CheckUrlParams(intUrl int64, stringUrl string, floatUrl float32, boolUrl bool, uuidUrl uuid.UUID, decimalUrl decimal.Decimal, dateUrl civil.Date) (*CheckUrlParamsResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+fmt.Sprintf("/check/url_params/%s/%s/%s/%s/%s/%s/%s", convertInt64(intUrl), stringUrl, convertFloat32(floatUrl), convertBool(boolUrl), convertUuid(uuidUrl), convertDecimal(decimalUrl), convertDate(dateUrl)), nil)
	operationId := "http.check.check_url_params"
	url := fmt.Sprintf("/check/url_params/%s/%s/%s/%s/%s/%s/%s", convertInt64(intUrl), stringUrl, convertFloat32(floatUrl), convertBool(boolUrl), convertUuid(uuidUrl), convertDecimal(decimalUrl), convertDate(dateUrl))
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
		body := &Empty
		log.WithFields(log.Fields{
			"json": *body,
		}).Info("Status code: 200")
		log.Info("Request completed")
		return &CheckUrlParamsResponse{Ok: body}, nil
	}

	log.Info("Request completed")
	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}

func (client *checkClient) CheckForbidden() (*CheckForbiddenResponse, error) {
	req, err := http.NewRequest("GET", client.baseUrl+"/check/forbidden", nil)
	operationId := "http.check.check_forbidden"
	url := "/check/forbidden"
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
		return &CheckForbiddenResponse{Ok: body}, nil
	}

	if resp.StatusCode == 403 {
		body := &Empty
		log.WithFields(log.Fields{
			"json": *body,
		}).Info("Status code: 403")
		log.Info("Request completed")
		return &CheckForbiddenResponse{Forbidden: body}, nil
	}

	log.Info("Request completed")
	return nil, errors.New(fmt.Sprintf("Unexpected status code received: %d", resp.StatusCode))
}
