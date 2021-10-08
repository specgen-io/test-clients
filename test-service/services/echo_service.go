package services

import "test-service/spec/echo"
import "test-service/spec/models"

type EchoService struct{}

func (service *EchoService) EchoBody(body *models.Message) (*echo.EchoBodyResponse, error) {
	return &echo.EchoBodyResponse{Ok: body}, nil
}
func (service *EchoService) EchoQuery(intQuery int, stringQuery string) (*echo.EchoQueryResponse, error) {
	return &echo.EchoQueryResponse{Ok: &models.Message{IntField: intQuery, StringField: stringQuery}}, nil
}
func (service *EchoService) EchoHeader(intHeader int, stringHeader string) (*echo.EchoHeaderResponse, error) {
	return &echo.EchoHeaderResponse{Ok: &models.Message{IntField: intHeader, StringField: stringHeader}}, nil
}
func (service *EchoService) EchoUrlParams(intUrl int, stringUrl string) (*echo.EchoUrlParamsResponse, error) {
	return &echo.EchoUrlParamsResponse{Ok: &models.Message{IntField: intUrl, StringField: stringUrl}}, nil
}

func (service *EchoService) SameOperationName() (*echo.SameOperationNameResponse, error) {
	return &echo.SameOperationNameResponse{Ok: &echo.Empty}, nil
}