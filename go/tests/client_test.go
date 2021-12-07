package tests

import (
	"cloud.google.com/go/civil"
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
	"gotest.tools/assert"
	"test-client/spec/check"
	"test-client/spec/echo"
	"test-client/spec/models"
	"testing"
)

var service_url = "http://localhost:8081"

func Test_Echo_Body_String(t *testing.T) {
	client := echo.NewClient(service_url)
	expectedMessage := "TWFueSBoYW5kcyBtYWtlIGxpZ2h0IHdvcmsu"
	response, err := client.EchoBodyString(expectedMessage)
	assert.NilError(t, err)
	assert.NilError(t, err, response)
	assert.DeepEqual(t, expectedMessage, *response)
}

func Test_Echo_Body(t *testing.T) {
	client := echo.NewClient(service_url)
	expectedMessage := &models.Message{123, "the string"}
	response, err := client.EchoBody(&models.Message{123, "the string"})
	assert.NilError(t, err)
	assert.NilError(t, err, response)
	assert.DeepEqual(t, expectedMessage, response)
}

func Test_Echo_Query(t *testing.T) {
	client := echo.NewClient(service_url)
	expectedMessage := &models.Message{123, "the string"}
	response, err := client.EchoQuery(123, "the string")
	assert.NilError(t, err)
	assert.NilError(t, err, response)
	assert.DeepEqual(t, expectedMessage, response)
}

func Test_Echo_Header(t *testing.T) {
	client := echo.NewClient(service_url)
	expectedMessage := &models.Message{123, "the string"}
	response, err := client.EchoHeader(123, "the string")
	assert.NilError(t, err)
	assert.NilError(t, err, response)
	assert.DeepEqual(t, expectedMessage, response)
}

func Test_Echo_Url_Params(t *testing.T) {
	client := echo.NewClient(service_url)
	expectedMessage := &models.Message{123, "the string"}
	response, err := client.EchoUrlParams(123, "the string")
	assert.NilError(t, err)
	assert.NilError(t, err, response)
	assert.DeepEqual(t, expectedMessage, response)
}

func Test_Check_Query_Params(t *testing.T) {
	client := check.NewClient(service_url)
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
	err := client.CheckQuery(pString, &pStringOpt, pStringArray, pDate, pDateArray, pDatetime, pInt, pLong, pDecimal, pEnum, pStringDefaulted)
	assert.NilError(t, err)
}

func Test_Check_Url_Params(t *testing.T) {
	client := check.NewClient(service_url)
	var intUrl int64 = 1234
	stringUrl := "the string"
	var floatUrl float32 = 1.23
	boolUrl := true
	uuidUrl, _ := uuid.Parse("123e4567-e89b-12d3-a456-426655440000")
	decimalUrl, _ := decimal.NewFromString("1.23")
	dateUrl, _ := civil.ParseDate("2019-11-30")
	enumUrl := models.Choice("SECOND_CHOICE")
	err := client.CheckUrlParams(intUrl, stringUrl, floatUrl, boolUrl, uuidUrl, decimalUrl, dateUrl, enumUrl)
	assert.NilError(t, err)
}

func Test_Check_Response_Forbidden(t *testing.T) {
	client := check.NewClient(service_url)
	response, err := client.CheckForbidden()
	assert.NilError(t, err)
	assert.NilError(t, err, response.Forbidden)
	assert.NilError(t, err, response.Ok)
}