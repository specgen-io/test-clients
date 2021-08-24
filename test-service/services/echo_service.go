package services

import "test-service/spec"

type EchoService struct{}

func (service *EchoService) EchoBody(body *spec.Message) (*spec.EchoBodyResponse, error) {
	return &spec.EchoBodyResponse{Ok: body}, nil
}
func (service *EchoService) EchoQuery(intQuery int, stringQuery string) (*spec.EchoQueryResponse, error) {
	return &spec.EchoQueryResponse{Ok: &spec.Message{IntField: intQuery, StringField: stringQuery}}, nil
}
func (service *EchoService) EchoHeader(intHeader int, stringHeader string) (*spec.EchoHeaderResponse, error) {
	return &spec.EchoHeaderResponse{Ok: &spec.Message{IntField: intHeader, StringField: stringHeader}}, nil
}
func (service *EchoService) EchoUrlParams(intUrl int, stringUrl string) (*spec.EchoUrlParamsResponse, error) {
	return &spec.EchoUrlParamsResponse{Ok: &spec.Message{IntField: intUrl, StringField: stringUrl}}, nil
}
