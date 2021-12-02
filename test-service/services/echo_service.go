package services

import (
	"test-service/spec/echo"
	"test-service/spec/models"
)

type EchoService struct{}

func (service *EchoService) EchoBodyString(body string) (*string, error) {
	return &body, nil
}
func (service *EchoService) EchoBody(body *models.Message) (*models.Message, error) {
	return body, nil
}
func (service *EchoService) EchoQuery(intQuery int, stringQuery string) (*models.Message, error) {
	return &models.Message{IntField: intQuery, StringField: stringQuery}, nil
}
func (service *EchoService) EchoHeader(intHeader int, stringHeader string) (*models.Message, error) {
	return &models.Message{IntField: intHeader, StringField: stringHeader}, nil
}
func (service *EchoService) EchoUrlParams(intUrl int, stringUrl string) (*models.Message, error) {
	return &models.Message{IntField: intUrl, StringField: stringUrl}, nil
}
func (service *EchoService) SameOperationName() (*echo.SameOperationNameResponse, error) {
	return &echo.SameOperationNameResponse{Ok: &echo.Empty}, nil
}
