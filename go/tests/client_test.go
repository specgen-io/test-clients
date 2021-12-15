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

var serviceUrl = "http://localhost:8081"

func Test_Echo_Body_String(t *testing.T) {
	client := echo.NewClient(serviceUrl)

	expectedMessage := "TWFueSBoYW5kcyBtYWtlIGxpZ2h0IHdvcmsu"
	response, err := client.EchoBodyString(expectedMessage)

	assert.NilError(t, err)
	assert.NilError(t, err, response)
	assert.DeepEqual(t, expectedMessage, *response)
}

func Test_Echo_Body(t *testing.T) {
	client := echo.NewClient(serviceUrl)

	expectedMessage := &models.Message{123, "the string"}
	response, err := client.EchoBody(&models.Message{123, "the string"})

	assert.NilError(t, err)
	assert.NilError(t, err, response)
	assert.DeepEqual(t, expectedMessage, response)
}

func Test_Echo_Query(t *testing.T) {
	client := echo.NewClient(serviceUrl)

	intQuery := 123
	var longQuery int64 = 12345
	var floatQuery float32 = 1.23
	doubleQuery := 12.345
	decimalQuery, _ := decimal.NewFromString("12345")
	boolQuery := true
	stringQuery := "the value"
	stringOptQuery := "the value"
	stringDefaultedQuery := "value"
	stringArrayQuery := []string{"the str1", "the str2"}
	uuidQuery, _ := uuid.Parse("123e4567-e89b-12d3-a456-426655440000")
	dateQuery, _ := civil.ParseDate("2020-01-01")
	dateFieldOne, _ := civil.ParseDate("2020-01-01")
	dateFieldTwo, _ := civil.ParseDate("2020-01-02")
	dateArrayQuery := []civil.Date{dateFieldOne, dateFieldTwo}
	datetimeQuery, _ := civil.ParseDateTime("2019-11-30T17:45:55")
	enumQuery := models.Choice("SECOND_CHOICE")

	expectedMessage := &models.Parameters{intQuery, longQuery, floatQuery, doubleQuery, decimalQuery, boolQuery, stringQuery, &stringOptQuery, stringDefaultedQuery, stringArrayQuery, uuidQuery, dateQuery, dateArrayQuery, datetimeQuery, enumQuery}
	response, err := client.EchoQuery(intQuery, longQuery, floatQuery, doubleQuery, decimalQuery, boolQuery, stringQuery, &stringOptQuery, stringDefaultedQuery, stringArrayQuery, uuidQuery, dateQuery, dateArrayQuery, datetimeQuery, enumQuery)
	assert.NilError(t, err)
	assert.NilError(t, err, response)
	assert.DeepEqual(t, expectedMessage, response)
}

func Test_Echo_Header(t *testing.T) {
	client := echo.NewClient(serviceUrl)

	intHeader := 123
	var longHeader int64 = 12345
	var floatHeader float32 = 1.23
	doubleHeader := 12.345
	decimalHeader, _ := decimal.NewFromString("12345")
	boolHeader := true
	stringHeader := "the value"
	stringOptHeader := "the value"
	stringDefaultedHeader := "value"
	stringArrayHeader := []string{"the str1", "the str2"}
	uuidHeader, _ := uuid.Parse("123e4567-e89b-12d3-a456-426655440000")
	dateHeader, _ := civil.ParseDate("2020-01-01")
	dateFieldOne, _ := civil.ParseDate("2020-01-01")
	dateFieldTwo, _ := civil.ParseDate("2020-01-02")
	dateArrayHeader := []civil.Date{dateFieldOne, dateFieldTwo}
	datetimeHeader, _ := civil.ParseDateTime("2019-11-30T17:45:55")
	enumHeader := models.Choice("SECOND_CHOICE")

	expectedMessage := &models.Parameters{intHeader, longHeader, floatHeader, doubleHeader, decimalHeader, boolHeader, stringHeader, &stringOptHeader, stringDefaultedHeader, stringArrayHeader, uuidHeader, dateHeader, dateArrayHeader, datetimeHeader, enumHeader}
	response, err := client.EchoHeader(intHeader, longHeader, floatHeader, doubleHeader, decimalHeader, boolHeader, stringHeader, &stringOptHeader, stringDefaultedHeader, stringArrayHeader, uuidHeader, dateHeader, dateArrayHeader, datetimeHeader, enumHeader)
	assert.NilError(t, err)
	assert.NilError(t, err, response)
	assert.DeepEqual(t, expectedMessage, response)
}

func Test_Echo_Url_Params(t *testing.T) {
	client := echo.NewClient(serviceUrl)

	intUrl := 123
	var longUrl int64 = 12345
	var floatUrl float32 = 1.23
	doubleUrl := 12.345
	decimalUrl, _ := decimal.NewFromString("12345")
	boolUrl := true
	stringUrl := "the value"
	uuidUrl, _ := uuid.Parse("123e4567-e89b-12d3-a456-426655440000")
	dateUrl, _ := civil.ParseDate("2020-01-01")
	datetimeUrl, _ := civil.ParseDateTime("2019-11-30T17:45:55")
	enumUrl := models.Choice("SECOND_CHOICE")

	expectedMessage := &models.UrlParameters{intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl}
	response, err := client.EchoUrlParams(intUrl, longUrl, floatUrl, doubleUrl, decimalUrl, boolUrl, stringUrl, uuidUrl, dateUrl, datetimeUrl, enumUrl)
	assert.NilError(t, err)
	assert.NilError(t, err, response)
	assert.DeepEqual(t, expectedMessage, response)
}

func Test_Check_Response_Forbidden(t *testing.T) {
	client := check.NewClient(serviceUrl)

	response, err := client.CheckForbidden()

	assert.NilError(t, err)
	assert.NilError(t, err, response.Forbidden)
	assert.NilError(t, err, response.Ok)
}
