init = function(args)
   request_uri = args[1]
   print("init")
   print(request_uri)
   depth = tonumber(args[2]) or 1
   print(depth)

   local r = {}
   for i=1,depth do
     r[i] = wrk.format(nil, request_uri)
     print(r[i])
   end
   req = table.concat(r)
end

request = function()
   wrk.headers["J-Tenant-Id"] = "1007"
   return req
end
