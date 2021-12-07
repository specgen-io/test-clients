import { test } from 'uvu';
import * as assert from 'uvu/assert';

import {Message, Choice} from '../test-service/models'
import {client as echoClient} from '../test-service/echo'
import {client as checkClient} from '../test-service/check'

const config = {baseURL: process.env.SERVICE_URL!}

test('echoBody', async function() {
  const client = echoClient(config)
  let body: Message = {int_field: 123, string_field: "the string"}
  let response = await client.echoBody({body})
  assert.equal(response, body, 'response matches request')
})


test('echoQuery', async function() {
  const client = echoClient(config)
  let expected: Message = {int_field: 123, string_field: "the string"}
  let response = await client.echoQuery({intQuery: 123, stringQuery: "the string"})
  assert.equal(response, expected, 'response matches expected')
})

test('echoHeader', async function() {
  const client = echoClient(config)
  let expected: Message = {int_field: 123, string_field: "the string"}
  let response = await client.echoHeader({intHeader: 123, stringHeader: "the string"})
  assert.equal(response, expected, 'response matches expected')
})

test('echoUrlParams', async function() {
  const client = echoClient(config)
  let expected: Message = {int_field: 123, string_field: "the string"}
  let response = await client.echoUrlParams({intUrl: 123, stringUrl: "the string"})
  assert.equal(response, expected, 'response matches expected')
})

test('checkQuery', async function() {
  let client = checkClient(config)
  let body: Message = {int_field: 123, string_field: "the string"}
    await client.checkQuery({
        pString: "the string",
        pStringArray: ["string 1", "string 2"],
        pDate: "2021-01-01",
        pDateArray: ["2021-01-02"],
        pDatetime: new Date("2021-01-02T23:54"),
        pInt: 123,
        pLong: 123,
        pDecimal: 123,
        pEnum: Choice.SECOND_CHOICE,
        pStringOpt: "some string",
        pStringDefaulted: "the string",
    })
})

test.run();