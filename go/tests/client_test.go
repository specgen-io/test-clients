package tests

import (
	"cloud.google.com/go/civil"
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
	"gotest.tools/assert"
	"test-client/spec"
	"test-client/spec/models"
	"testing"
)

var service_url = "http://localhost:8081"

func Test_Echo_Body(t *testing.T) {
	client := spec.NewEchoClient(service_url)
	expectedMessage := &models.Message{123, "the string"}
	response, err := client.EchoBody(&models.Message{123, "the string"})
	assert.NilError(t, err)
	assert.NilError(t, err, response.Ok)
	assert.DeepEqual(t, expectedMessage, response.Ok)
}

func Test_Echo_Query(t *testing.T) {
	client := spec.NewEchoClient(service_url)
	expectedMessage := &models.Message{123, "the string"}
	response, err := client.EchoQuery(123, "the string")
	assert.NilError(t, err)
	assert.NilError(t, err, response.Ok)
	assert.DeepEqual(t, expectedMessage, response.Ok)
}

func Test_Echo_Header(t *testing.T) {
	client := spec.NewEchoClient(service_url)
	expectedMessage := &models.Message{123, "the string"}
	response, err := client.EchoHeader(123, "the string")
	assert.NilError(t, err)
	assert.NilError(t, err, response.Ok)
	assert.DeepEqual(t, expectedMessage, response.Ok)
}

func Test_Echo_Url_Params(t *testing.T) {
	client := spec.NewEchoClient(service_url)
	expectedMessage := &models.Message{123, "the string"}
	response, err := client.EchoUrlParams(123, "the string")
	assert.NilError(t, err)
	assert.NilError(t, err, response.Ok)
	assert.DeepEqual(t, expectedMessage, response.Ok)
}

func Test_Check_Query_Params(t *testing.T) {
	client := spec.NewCheckClient(service_url)
	pString := "the string 1"
	pStringOpt := "the string 2"
	pStringArray := []string{"str1", "str2"}
	pDate, _ := civil.ParseDate("2020-01-01")
	pDateFieldOne, _ := civil.ParseDate("2020-01-01")
	pDateFieldTwo, _ := civil.ParseDate("2020-01-02")
	pDateArray := []civil.Date{pDateFieldOne, pDateFieldTwo}
	pDatetime, _ := civil.ParseDateTime("2019-11-30T17:45:55")
	pInt := 123
	var pLong int64 = 1234
	pDecimal, _ := decimal.NewFromString("1.23")
	pEnum := models.Choice("SECOND_CHOICE")
	pStringDefaulted := "value"
	response, err := client.CheckQuery(pString, &pStringOpt, pStringArray, pDate, pDateArray, pDatetime, pInt, pLong, pDecimal, pEnum, pStringDefaulted)
	assert.NilError(t, err)
	assert.NilError(t, err, response.Ok)
}

func Test_Check_Url_Params(t *testing.T) {
	client := spec.NewCheckClient(service_url)
	var longField int64 = 1234
	uuidField, _ := uuid.Parse("123e4567-e89b-12d3-a456-426655440000")
	decimalField, _ := decimal.NewFromString("1.23")
	dateField, _ := civil.ParseDate("2019-11-30")
	response, err := client.CheckUrlParams(longField, "the string", 1.23, true, uuidField, decimalField, dateField)
	assert.NilError(t, err)
	assert.NilError(t, err, response.Ok)
}

func Test_Check_Response_Forbidden(t *testing.T) {
	client := spec.NewCheckClient(service_url)
	response, err := client.CheckForbidden()
	assert.NilError(t, err)
	assert.NilError(t, err, response.Forbidden)
	assert.NilError(t, err, response.Ok)
}